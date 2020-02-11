package InformationCore;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InformationStationsParser {
    private Elements informationAboutStations;

    private TreeMap<String,String> lines = new TreeMap<>(); //{NumberLine, NameLine}
    private TreeMap<String, ArrayList<String>> stationsMap = new TreeMap<>(); //{Number ListConnectsStations}
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

        return new String[]{getCleanNumberLine(numberLine), nameLine};
    }

    private String[] getStationInformation(Element informationAboutStation) {
        String numberLine = getLineInformation(informationAboutStation)[0];
        String stationName = informationAboutStation.select("a").get(1).attr("title");
        return new String[]{numberLine, getClearNameStation(stationName)};
    }


    private ArrayList<String[]> getConnectsInformation(Element informationAboutStation) {
        String[] mainStation = getStationInformation(informationAboutStation);
        ArrayList<String[]> connectStations = new ArrayList<>();
        connectStations.add(mainStation);
        Elements elements = informationAboutStation.select("td").select("span.sortkey");
        for (int i = 2; i< elements.size(); i++) {
            String numberLine = elements.get(i).text();
            String stationName = elements.get(i).nextSibling().attr("title");

            if (stationName.length() != 0) {
                connectStations.add(new String[]{getCleanNumberLine(numberLine),getClearNameConnection(stationName)});
            }
        }
        if (connectStations.size() == 1) {
            return null;
        }
        return connectStations; // {MainStation, ArrayList<[]Connects>} - > {Station - {Line, Stations}}
    }
    //---------------------------------------------------------------------------------------------------------------

    private String getClearNameStation (String dirtyNameStation) {
        String string = dirtyNameStation;
        Pattern pattern = Pattern.compile("\\([А-Яа-я\\s+,.\\-ё]+\\)");
        Matcher m = pattern.matcher(string);
        if (m.find()) {
            string = string.substring(0,m.start());
        }
        return string.trim();
    }

    private String getClearNameConnection (String dirtyNameConnection) {
        String string = dirtyNameConnection;
        Pattern pattern = Pattern.compile("на станцию ");
        Pattern pattern1 = Pattern.compile(" Московского центрального кольца");
        Pattern pattern2 = Pattern.compile("[А-Яа-яё]+-?[А-Яа-яё]+ линии");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            string = string.substring(matcher.end());
        }
        Matcher matcher1 = pattern1.matcher(string);
        if (matcher1.find()) {
            string = string.substring(0,matcher1.start());
        }
        Matcher matcher2 = pattern2.matcher(string);
        if (matcher2.find()) {
            string = string.substring(0,matcher2.start());
        }

//        Pattern pattern = Pattern.compile("\\([А-Яа-я\\s+,.\\-ё]+\\)");
//        Matcher m = pattern.matcher(string);
//        if (m.find()) {
//            string = string.substring(0,m.start());
//        }
        return string.trim();
    }



    private String getCleanNumberLine (String dirtyNumberLine) {
        if (dirtyNumberLine.charAt(dirtyNumberLine.length()-1) == 'А') {
            if (dirtyNumberLine.length() > 3) {
                dirtyNumberLine = dirtyNumberLine.substring(1);
            }
            else {
                dirtyNumberLine = "0" + dirtyNumberLine;
            }
        }
        return dirtyNumberLine;
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
