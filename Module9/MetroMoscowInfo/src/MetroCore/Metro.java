package MetroCore;

import MetroCore.Components.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Metro {

    private JSONObject information;
    private JSONParser parser = new JSONParser();
    private ArrayList<Line> lines = new ArrayList<>();
    private Map<Line, ArrayList<Station>> mapStationsOfLine = new HashMap<>();
    private Map<String,ArrayList<Station>> mapConnectStationsOfStation = new HashMap<>();


    public Metro(String pathJSonFileMetro) {
        information = getJsonFile(pathJSonFileMetro);
        parsJSonFile();
    }


    private void parsJSonFile() {
        JSONArray linesObject = (JSONArray) information.get("Lines");
        parseLines(linesObject);

        JSONObject stationsObject = (JSONObject) information.get("Station");
        parseStations(stationsObject);

        JSONArray connectionsObject = (JSONArray) information.get("Connection");
        parseConnections(connectionsObject);
    }

    private void parseLines(JSONArray linesObject) {
        lines = new ArrayList<>();
        for (Object lineObject : linesObject) {
            JSONObject lineJSonObject = (JSONObject) lineObject;
            String number = (String) lineJSonObject.get("Number");
            String name = (String) lineJSonObject.get("Name");
            lines.add(new Line(number,name));
        }
    }

    private void parseStations(JSONObject stationsObject)
    {
        for (Object lineNumberObject : stationsObject.keySet()) {
            String lineNumber = String.valueOf(lineNumberObject);

            JSONArray arrayStationsObject = (JSONArray) stationsObject.get(lineNumberObject);
            ArrayList<Station> stationsList = new ArrayList<>();
            for (Object station : arrayStationsObject) {
                stationsList.add(new Station(lineNumber,station.toString()));
            }
            Line line = getLineOfNumber(lineNumber);
            if (line != null) {
                mapStationsOfLine.put(line, stationsList);
            }
        }
    }

    private void parseConnections(JSONArray connectionsObject) {
        try {
            for (Object connectInOnePlace : (JSONArray) parser.parse(connectionsObject.toString())) {
                JSONArray stations = (JSONArray) parser.parse(connectInOnePlace.toString());
                Station main = null;
                ArrayList<Station> list = new ArrayList<>();
                for (int i = 0 ; i < stations.size(); i++) {
                    JSONObject jsonStation = (JSONObject) stations.get(i);
                    String numberLine = (String) jsonStation.get("Line");
                    String nameStation = (String) jsonStation.get("Station");
                    Station station = new Station(numberLine, nameStation);
                    if (i == 0) {
                        main = station;
                    }
                    else {
                        list.add(station);
                    }
                }
                mapConnectStationsOfStation.put(main.getName(), list);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    //---

    private JSONObject getJsonFile(String path)
    {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.forEach(builder::append);
            return (JSONObject) parser.parse(builder.toString());
        }
        catch (Exception ex) {
            System.out.println("File не найден");
            ex.printStackTrace();
        }
        return null;
    }

    private Line getLineOfNumber (String number) {
        for (Line line : lines) {
            if (line.getNumber().equals(number)) {
                return line;
            }
        }
        return null;
    }

    private Station getStation (Line line, String stationName) {
        for (Station station : mapStationsOfLine.get(line)) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        System.out.println(line.getName() + line.getNumber() + stationName);
        System.err.println("Станция не найдена");
        return null;
    }

    //---

    public ArrayList<Line> getLines() {
        return lines;
    }

    public Map<Line, ArrayList<Station>> getMapStationsOfLine() {
        return mapStationsOfLine;
    }

    public Map<String, ArrayList<Station>> getMapConnectStationsOfStation() {
        return mapConnectStationsOfStation;
    }

}
