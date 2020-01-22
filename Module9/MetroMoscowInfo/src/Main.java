public class Main {
    public static void main(String[] args) {
        String html = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";
        MetroSiteParser metroSiteParser = new MetroSiteParser(html);
        InformationStationsParser informationStationsParser = new InformationStationsParser(metroSiteParser.getInformationAboutStations());
        InformationStationsJSon jsonInformation = new InformationStationsJSon(informationStationsParser);
        String path = "JsonFiles\\test.json";
        jsonInformation.saveJsonFile(path);
    }
}

