import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    private Station station1toLine1;
    private Station station2toLine1;
    private Station station3toLine1;

    private Station station1toLine2;
    private Station station2toLine2;
    private Station station3toLine2;

    private Station station1toLine3;
    private Station station2toLine3;
    private Station station3toLine3;


    private StationIndex stationIndex;

    private RouteCalculator routeCalculator;


    // https://sun9-40.userapi.com/c850424/v850424107/1ba968/P7eAeUc9rwU.jpg
    // Схема метро графически
    // Пример: 3.2 - Станция 3 Линия 2;

    @Override
    protected void setUp() throws Exception {

        //make new Line
        Line line1 = new Line(1, "ПерваяЛиния");
        Line line2 = new Line(2, "ВтораяЛиния");
        Line line3 = new Line(3, "ТретьяЛиния");

        //make new Station
        station1toLine1 = new Station("Станция1наПервойЛиния", line1);
        station2toLine1 = new Station("Станция2наПервойЛиния", line1);
        station3toLine1 = new Station("Станция3наПервойЛиния", line1);

        station1toLine2 = new Station("Станция1наВторойЛинии", line2);
        station2toLine2 = new Station("Станция2наВторойЛинии", line2);
        station3toLine2 = new Station("Станция3наВторойЛинии", line2);

        station1toLine3 = new Station("Станция1наТретейЛинии", line3);
        station2toLine3 = new Station("Станция2наТретейЛинии", line3);
        station3toLine3 = new Station("Станция3наТретейЛинии", line3);

        //add station to Line
        line1.addStation(station1toLine1);
        line1.addStation(station2toLine1);
        line1.addStation(station3toLine1);

        line2.addStation(station1toLine2);
        line2.addStation(station2toLine2);
        line2.addStation(station3toLine2);

        line3.addStation(station1toLine3);
        line3.addStation(station2toLine3);
        line3.addStation(station3toLine3);

        //add line to StationIndex
        stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(station1toLine1);
        stationIndex.addStation(station2toLine1);
        stationIndex.addStation(station3toLine1);

        stationIndex.addStation(station1toLine2);
        stationIndex.addStation(station2toLine2);
        stationIndex.addStation(station3toLine2);

        stationIndex.addStation(station1toLine3);
        stationIndex.addStation(station2toLine3);
        stationIndex.addStation(station3toLine3);

        //add Connection of Station
        List<Station> connection1 = new ArrayList<>();
        connection1.add(station2toLine1);
        connection1.add(station2toLine2);
        stationIndex.addConnection(connection1);

        List<Station> connection2 = new ArrayList<>();
        connection2.add(station3toLine2);
        connection2.add(station3toLine3);
        stationIndex.addConnection(connection2);

        //Создаем сам routeCalculator с заполненым stationIndex
        routeCalculator = new RouteCalculator(stationIndex);
    }


    public void testCalculateDurationOnOneLine() //Без Пересадок
    {
        List<Station> route = new ArrayList<>();

        route.add(station1toLine1);
        route.add(station2toLine1);
        route.add(station3toLine1);

        double actualDuration = RouteCalculator.calculateDuration(route);
        double expectedDuration = 2.5 + 2.5;

        assertEquals(actualDuration, expectedDuration);
    }

    public void testCalculateDurationOnTwoLine() //Одна Персадка
    {
        List<Station> route = new ArrayList<>();

        route.add(station1toLine1);
        route.add(station2toLine1);
        //Пересадка c 2.1 -> 2.2
        route.add(station2toLine2);
        route.add(station3toLine2);

        double actualDuration = RouteCalculator.calculateDuration(route);
        double expectedDuration = 2.5 + 3.5 + 2.5;

        assertEquals(actualDuration, expectedDuration);
    }


    public void testCalculateDurationOnThreeLine() //Две Пересадки
    {

        List<Station> route = new ArrayList<>();

        route.add(station1toLine1);
        route.add(station2toLine1);
        //Пересадка c 2.1 -> 2.2
        route.add(station2toLine2);
        route.add(station3toLine2);
        //Пересадка c 3.2 -> 3.3
        route.add(station3toLine3);
        route.add(station2toLine3); //Ошибочка
        route.add(station1toLine3);

        double actualDuration = RouteCalculator.calculateDuration(route);
        double expectedDuration = 2.5 + 3.5 + 2.5 + 3.5 + 2.5 + 2.5;

        assertEquals(actualDuration, expectedDuration);
    }


    public void testGetShortestRouteStationOnOneLine() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1toLine1, station3toLine1);

        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(station1toLine1);
        expectedRoute.add(station2toLine1);
        expectedRoute.add(station3toLine1);

        assertEquals(actualRoute, expectedRoute);
    }

    public void testGetShortestRouteStationOnTwoLine() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1toLine1, station3toLine2);

        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(station1toLine1);
        expectedRoute.add(station2toLine1);
        expectedRoute.add(station2toLine2);
        expectedRoute.add(station3toLine2);

        assertEquals(actualRoute, expectedRoute);
    }

    public void testGetShortestRouteStationOnThreeLine() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1toLine1, station1toLine3);
        System.out.println(actualRoute);

        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(station1toLine1);
        expectedRoute.add(station2toLine1);
        expectedRoute.add(station2toLine2);
        expectedRoute.add(station3toLine2);
        expectedRoute.add(station3toLine3);
        expectedRoute.add(station2toLine3);
        expectedRoute.add(station1toLine3);

        assertEquals(actualRoute, expectedRoute);
    }
}
