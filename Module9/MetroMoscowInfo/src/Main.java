import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        String html = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
        MetroSiteParser metroSiteParser = new MetroSiteParser(html);
        InformationStationsParser informationStationsParser = new InformationStationsParser(metroSiteParser.getInformationAboutStations());
        HashMap<String, ArrayList<String>> list = informationStationsParser.getListStations();

//        Set<String> keys = list.keySet();
//        for (String key : keys) {
//            System.out.println(key);
//            for (String station : list.get(key)) {
//                System.out.println("\t" + station);
//            }
//        }
    }
}

