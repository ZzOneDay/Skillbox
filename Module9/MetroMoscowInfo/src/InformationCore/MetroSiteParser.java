package InformationCore;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MetroSiteParser {

    private Elements stations;

    public MetroSiteParser(String pathHtmlMetroSite) {
        stations = new Elements();
        Document information = null;
        try {
            information = getDocument(pathHtmlMetroSite);
        } catch (IOException e) {
            System.out.println("не удалось получить доступ к сайту");
            e.printStackTrace();
        }
        if (information != null) {
            stations.addAll(getStationsOfInformation(information));
        }
    }

    private Document getDocument(String html) throws IOException {
        return Jsoup.connect(html)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .maxBodySize(0)
                .get();
    }

    private Elements getStationsOfInformation(Document Information) {
        Elements stationsElements = new Elements();
        Elements elements = Information.select("div.mw-parser-output table");
        Elements underMetro = elements.get(2).select("tr");
        Elements monoRail = elements.get(3).select("tr");
        Elements circleMetro = elements.get(4).select("tr");

        stationsElements.addAll(getOnlyStations(underMetro, 1));
        stationsElements.addAll(getOnlyStations(monoRail, 2));
        stationsElements.addAll(getOnlyStations(circleMetro, 2));
        return stationsElements;
    }

    public void saveJSonFile(String path) {
        InformationStationsParser informationStationsParser = new InformationStationsParser(stations);
        InformationStationsJSon jsonInformation = new InformationStationsJSon(informationStationsParser);
        jsonInformation.saveJsonFile(path); //save file
    }

    private Elements getOnlyStations(Elements addStations, int indexStartElements) {
        Elements stations = new Elements();
        for (int i = indexStartElements; i < addStations.size(); i++) {
            stations.add(addStations.get(i));
        }
        return stations;
    }
}
