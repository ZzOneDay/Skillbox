import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InformationStationsParser {
    private Elements informationAboutStations;

    HashMap<String, ArrayList<String>> getListStations() {
        return listStations;
    }

    private HashMap<String, ArrayList<String>> listStations;

    InformationStationsParser(Elements informationAboutStations) {
        this.informationAboutStations = informationAboutStations;
        listStations = new HashMap<>();
        parseInformationAboutStations();
    }

    private void parseInformationAboutStations() {
        //ArrayList<String[]> listConnect = getConnectsInformation(informationAboutStations.get(10));
//        for(String[] connect : listConnect) {
//            System.out.println("Number " + connect[0] + " stationName : " + connect[1]);
//        }

        for (Element information : informationAboutStations) {
            String[] informationLine = getLineInformation(information);
            String[] informationStation = getStationInformation(information);
            System.out.println("NumberLine : " + informationLine[0] + "; NameLine : " + informationLine[1]);
            System.out.println("NumberLine : " + informationStation[0] + "; NameStation : " + informationStation[1]);
            ArrayList<String[]> listConnect = getConnectsInformation(information);
            System.out.println("Пересадки");
            for(String[] connect : listConnect) {
                System.out.println("Number " + connect[0] + " stationName : " + connect[1]);
            }
            System.out.println("-----------------------------------------------------------------------------");
        }
    }

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
            String station = getTextOfTitle(elements.get(i+1).toString());
            listConnect.add(new String[]{number,station.substring(19)});
        }
//        if (listConnect.size() != 0) {
//            listConnect.add(getStationInformation(informationAboutStation)); //Станция с которой переходят
//        }
        return listConnect; // {ArrayList<[]Connects> - > {Line, Stations}}
    }

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
            return "0"+information;
        }
        return getOnlyInteger(information);
    }

    private String getOnlyInteger(String text) {
        return text.replaceAll("[^0-9.]+", "");
    }
}
