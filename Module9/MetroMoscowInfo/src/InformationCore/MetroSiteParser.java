package InformationCore;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MetroSiteParser {

    private Elements informationAboutStations;

    public MetroSiteParser(String pathHtmlMetroSite) {
        try {
            Document informationAboutMetro = getDocument(pathHtmlMetroSite);
            informationAboutStations = getStationsOfInformation(informationAboutMetro);
        } catch (IOException e) {
            System.out.println("не удалось получить доступ к сайту");
            e.printStackTrace();
        }
    }

    private Document getDocument(String html) throws IOException {
        return Jsoup.connect(html)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
    }

    private Elements getStationsOfInformation(Document InformationAboutMetro) {
        Elements elements = InformationAboutMetro.select("#mw-content-text > div > table:nth-child(7) > tbody");
        Elements stations = new Elements();
        elements = elements.select("tr");
        int size = elements.size();
        for (int i = 2; i < size; i++) {
            stations.add(elements.select("tr:nth-child(" + i + ")").get(0));
        }
        return stations;
    }

    public void saveJSonFile (String path) {
        InformationStationsParser informationStationsParser = new InformationStationsParser(informationAboutStations);
        InformationStationsJSon jsonInformation = new InformationStationsJSon(informationStationsParser);
        jsonInformation.saveJsonFile(path); //save file
    }
}
