import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//    0 - 13.02.1989 - Mon

public class Main
{
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date nowDay = calendar.getTime();

//        calendar.set(1993,Calendar.MARCH,1);
        calendar.set(1996,Calendar.SEPTEMBER,5);

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat dayFormat = new SimpleDateFormat("EE", Locale.ENGLISH);

        int yearBirthday = Integer.parseInt(yearFormat.format(calendar.getTime()));
        int yearNow = Integer.parseInt(yearFormat.format(nowDay));

        int fullAgeInThisYear =  yearNow - yearBirthday;

        for (int ageNow = 0; ageNow <= fullAgeInThisYear; ageNow++)
        {
            System.out.println(ageNow + " - "
                    + dateFormat.format(calendar.getTime())
                    + " - " + dayFormat.format(calendar.getTime())) ;
            calendar.add(Calendar.YEAR, 1);
            if (calendar.getTime().getTime() > System.currentTimeMillis())
            {
                //День рожденье в этом году еще не наступило
                break;
            }
        }
    }
}
