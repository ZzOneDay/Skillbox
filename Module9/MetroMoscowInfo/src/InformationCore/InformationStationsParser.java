package InformationCore;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

class InformationStationsParser {
    private Elements informationAboutStations;

    private TreeMap<String,String> lines = new TreeMap<>(); //{NumberLine, NameLine}
    private TreeMap<String, ArrayList<String>> stationsMap = new TreeMap<>(); //{NumberLine, NameStation}
    private ArrayList<ArrayList<String[]>> connectsList = new ArrayList<>(); //{Station[], connectStations[]}


    InformationStationsParser(Elements informationAboutStations) {
        this.informationAboutStations = informationAboutStations;
        parseInformationAboutStations();
    }

    private void parseInformationAboutStations() {
        for (Element information : informationAboutStations) {
            String[] line = getLineInformation(information);
            lines.put(line[0],line[1]);

            String[] station = getStationInformation(information);
            addToStationsMap(station);

            ArrayList<String[]> list = getConnectsInformation(information);
            if (list != null) {
                connectsList.add(list);
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------

    private String[] getLineInformation(Element informationAboutStation) {
        String numberLine = informationAboutStation.select("td").get(0).select("span.sortkey").get(0).text();
        String nameLine = informationAboutStation.select("a").get(0).attr("title");
        return new String[]{numberLine, nameLine};
    }

    private String[] getStationInformation(Element informationAboutStation) {
        String numberLine = getLineInformation(informationAboutStation)[0];
        String stationName = informationAboutStation.select("a").get(1).attr("title");
        return new String[]{numberLine, stationName};
    }


    private ArrayList<String[]> getConnectsInformation(Element informationAboutStation) {
        String[] mainStation = getStationInformation(informationAboutStation);
        ArrayList<String[]> connectStations = new ArrayList<>();
        connectStations.add(mainStation);
        Elements elements = informationAboutStation.select("td").select("span.sortkey");
        for (int i = 2; i< elements.size(); i++) {
            String numberLine = elements.get(i).text();
            String stationName = elements.get(i).parent().select("a").attr("title");

            connectStations.add(new String[]{numberLine,stationName});
        }
        if (connectStations.size() == 1) {
            return null;
        }
        return connectStations; // {MainStation, ArrayList<[]Connects>} - > {Station - {Line, Stations}}
    }

    //---------------------------------------------------------------------------------------------------------------

    private void addToStationsMap(String[] station) {
        if (stationsMap.containsKey(station[0])) {
            stationsMap.get(station[0]).add(station[1]);
        } else {
            ArrayList<String> stationsOnLine = new ArrayList<>();
            stationsOnLine.add(station[1]);
            stationsMap.put(station[0], stationsOnLine);
        }
    }

    //---------------------------------------------------------------------------------------------------------------

    TreeMap<String,String> getLines() {
        return lines;
    }

    TreeMap<String, ArrayList<String>> getStationsMap() {
        return stationsMap;
    }


    ArrayList<ArrayList<String[]>> getConnectsList() {
        return connectsList;
    }


}
