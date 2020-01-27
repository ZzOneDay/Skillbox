package InformationCore;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InformationStationsParser {
    private Elements informationAboutStations;

    private ArrayList<String[]> lines = new ArrayList<>(); //{NumberLine, NameLine}
    private TreeMap<String, ArrayList<String>> stationsMap = new TreeMap<>(); //{NumberLine, NameStation}
    private ArrayList<ArrayList<String[]>> connectsList = new ArrayList<>(); //{NumberLine, NameStation}


    InformationStationsParser(Elements informationAboutStations) {
        this.informationAboutStations = informationAboutStations;
        parseInformationAboutStations();
    }

    private void parseInformationAboutStations() {
        String numberLine = "";
        for (Element information : informationAboutStations) {
            String[] line = getLineInformation(information);
            if (!numberLine.equals(line[0])) {
                lines.add(line);
                numberLine = line[0];
            }

            String[] station = getStationInformation(information);
            addToStationsMap(station);

            ArrayList<String[]> connectInOnePlace = getConnectsInformation(information);
            if (connectInOnePlace.size() != 0) {
                connectInOnePlace.add(0, station);
                connectsList.add(connectInOnePlace);
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------

    private String[] getLineInformation(Element informationAboutStation) {
        String lineInformation = informationAboutStation.select("td").get(0).toString(); // {NumberLine, NameLine}
        String numberLine = getNumberLine(lineInformation);
        String nameLine = getTextOfTitle(lineInformation);
        return new String[]{numberLine, nameLine};
    }

    private String[] getStationInformation(Element informationAboutStation) {
        String stationInformation = informationAboutStation.select("td").get(1).toString(); // {NumberLine, NameStation}
        String numberLine = getLineInformation(informationAboutStation)[0];
        String stationName = getTextOfTitle(stationInformation);
        return new String[]{numberLine, stationName};
    }


    private ArrayList<String[]> getConnectsInformation(Element informationAboutStation) {
        Element informationConnect = informationAboutStation.select("td").get(3);
        Elements elements = informationConnect.select("span");
        ArrayList<String[]> listConnect = new ArrayList<>();

        for (int i = 0; i < elements.size(); i = i + 2) {
            String number = getOnlyInteger(elements.get(i).toString());
            String station = getTextOfTitle(elements.get(i + 1).toString());
            listConnect.add(new String[]{number, station.substring(19)});
        }
        return listConnect; // {ArrayList<[]Connects> - > {Line, Stations}}
    }

    //---------------------------------------------------------------------------------------------------------------

    private String getTextOfTitle(String information) {
        Pattern pattern = Pattern.compile("\" title=\"");
        Matcher matcher = pattern.matcher(information);
        if (matcher.find()) {
            information = information.substring(matcher.end());
        }
        int lastIndex = information.indexOf("\"");
        return information.substring(0, lastIndex);
    }

    private String getNumberLine(String information) {
        Pattern pattern = Pattern.compile("<td data-sort-value=\"[0-9.]+\"");
        Matcher matcher = pattern.matcher(information);
        if (matcher.find()) {
            information = information.substring(matcher.start(), matcher.end());
        }
        information = getOnlyInteger(information);
        if (information.length() == 1) {
            return "0" + information;
        }
        return getOnlyInteger(information);
    }

    private String getOnlyInteger(String text) {
        return text.replaceAll("[^0-9.]+", "");
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

    ArrayList<String[]> getLines() {
        return lines;
    }

    TreeMap<String, ArrayList<String>> getStationsMap() {
        return stationsMap;
    }

    ArrayList<ArrayList<String[]>> getConnectsList() {
        return connectsList;
    }
}
