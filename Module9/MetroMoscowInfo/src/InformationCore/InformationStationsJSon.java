package InformationCore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

class InformationStationsJSon {
    private InformationStationsParser informationStationsParser;

    InformationStationsJSon(InformationStationsParser informationStationsParser) {
        this.informationStationsParser = informationStationsParser;
    }

    void saveJsonFile(String path) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter(path)) {
            gson.toJson(getMainJsonObjectToWrite(), file);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private JSONObject getMainJsonObjectToWrite() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Station", getStations());
        jsonObject.put("Connection", getConnections());
        jsonObject.put("Lines", getLines());
        return jsonObject;
    }

    private JSONObject getStations() {
        JSONObject stations = new JSONObject();
        stations.putAll(informationStationsParser.getStationsMap());
        return stations;
    }

    private JSONArray getConnections() {
        JSONArray connections = new JSONArray();
        for (ArrayList<String[]> connects : informationStationsParser.getConnectsList()) {
            JSONArray connectionsArrayInOnePlace = new JSONArray();
            for (String[] connectInOnePlace : connects) {
                JSONObject connect = new JSONObject();
                connect.put("Line", connectInOnePlace[0]);
                connect.put("Station", connectInOnePlace[1]);
                connectionsArrayInOnePlace.add(connect);
            }
            connections.add(connectionsArrayInOnePlace);
        }
        return connections;
    }

    private JSONArray getLines() {
        JSONArray lines = new JSONArray();
        Set<String> keySet = informationStationsParser.getLines().keySet();
        for (String key : keySet) {
                JSONObject line = new JSONObject();
                line.put("Number", key);
                line.put("Name", informationStationsParser.getLines().get(key));
                lines.add(line);
            }
        return lines;
    }
}
