import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loader {
    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.YYYY");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }
}
