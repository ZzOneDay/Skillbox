import InformationCore.*;
import MetroCore.Components.Line;
import MetroCore.Components.Station;
import MetroCore.Metro;
import java.util.ArrayList;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        String html = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
        String path = "JsonFiles\\test.json";

        MetroSiteParser metroSiteParser = new MetroSiteParser(html);
        metroSiteParser.saveJSonFile(path); //save file

        Metro metro = new Metro(path); //load file
        ArrayList<Line> lines = metro.getLines();
        Map<Line, ArrayList<Station>> stationMap = metro.getMapStationsOfLine();
        Map<Station, ArrayList<Station>> stationMapConnects = metro.getMapConnectStationsOfStation();

        System.out.println("Moscow Metro");
        for (Line line : lines) {
            System.out.println("Line number: " + line.getNumber() + " Name Line: " + line.getName());
            for (Station station : stationMap.get(line)) {
                System.out.println("\tСтанция: " + station.getName().toUpperCase());
                if (stationMapConnects.containsKey(station)) {
                    for (Station connect : stationMapConnects.get(station)) {
                        if (connect != null) {
                            Line someLine = metro.getLineOfNumber(connect.getNumberLine());
                            System.out.println("\t\tПереход на станцию: " + connect.getName()  + ", " + someLine.getName() + ";");
                        }
                    }
                }
            }
            System.out.println();
        }
    }
}

