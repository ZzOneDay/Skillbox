import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date date2hTo = calendar.getTime();


        Airport.getInstance().getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(x -> (dateNow.getTime() < x.getDate().getTime()  && x.getDate().getTime() < date2hTo.getTime()))
                .filter(x -> x.getType().equals(Flight.Type.DEPARTURE))
                .sorted(Comparator.comparing(Flight::getDate)) //Ну это уж от себя :)
                .forEach(System.out :: println);


//        terminals.map(Terminal::getFlights).flatMap(Collection::stream).filter(Main::inTheNext2hours).filter(Main::flightIsDeparture).forEach(System.out :: println);
//        terminals.flatMap(x -> x.getFlights().stream()).filter(x -> inTheNext2hours(x)).filter(x->flightIsDeparture(x)).forEach(System.out :: println);
    }


    static private boolean flightIsDeparture(Flight flight) {
        return flight.getType().equals(Flight.Type.DEPARTURE);
    }

    static private boolean inTheNext2hours(Flight flight) {
        boolean inTheNext2hours = false;
        Calendar calendar = Calendar.getInstance();
        Date dateNow = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date date2hTo = calendar.getTime();
        if (dateNow.getTime() < flight.getDate().getTime() && flight.getDate().getTime() < date2hTo.getTime()) {
            inTheNext2hours = true;
        }
        return inTheNext2hours;
    }
}