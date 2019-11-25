import com.skillbox.airport.Airport;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        System.out.println("Самолеты в Аэропорту:");
        for (int i = 0; i < airport.getAllAircrafts().size(); i++)
        {
            System.out.println(airport.getAllAircrafts().get(i));
        }
        System.out.println("Всего " + airport.getAllAircrafts().size() + " самолетов");
    }
}
