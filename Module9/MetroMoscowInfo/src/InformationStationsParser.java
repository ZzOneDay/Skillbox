import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InformationStationsParser
{
    private Elements informationAboutStations;

    HashMap<String, ArrayList<String>> getListStations() {
        return listStations;
    }

    private HashMap<String, ArrayList<String>> listStations;

    InformationStationsParser (Elements informationAboutStations) {
        this.informationAboutStations = informationAboutStations;
        listStations = new HashMap<>();
        parseInformationAboutStations();
    }

    private void parseInformationAboutStations () {
        for (Element station : informationAboutStations) {
            String[] info = parseStation(station);
            addToList(info);
        }
    }

    private String[] parseStation (Element informationAboutStation) {
        String lineName = informationAboutStation.select("td").get(0).toString();
        String stationName = informationAboutStation.select("td").get(1).toString();
        return new String[]{getTextOfTitle(lineName),getTextOfTitle(stationName)};
    }

    private String getTextOfTitle (String information) {
        Pattern pattern = Pattern.compile("\" title=\"");
        Matcher matcher = pattern.matcher(information);
        if (matcher.find()) {
            information = information.substring(matcher.start());
        }
        return clearText(information);
    }

    private String clearText (String text) {
        //"some information" - > some information
        int firstIndex = text.indexOf("title=\"")+7;
        text = text.substring(firstIndex);
        int lastIndex = text.indexOf("\"");
        return text.substring(0,lastIndex);
    }

    private void addToList (String[] info) {
        String line = info[0];
        String station = info[1];
        if (listStations.containsKey(line)) {
            listStations.get(line).add(station);
        }
        else {
            ArrayList<String> stations = new ArrayList<>();
            stations.add(station);
            listStations.put(line,stations);
        }
    }
}
