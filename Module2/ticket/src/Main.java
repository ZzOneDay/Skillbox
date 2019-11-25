public class Main {
    //от 200000 до 210000
    //от 220000 до 235000

    public static void main(String[] args) {

        for (int i = 200000; i <= 235000; i++)
        {
            if (i==210001)
            {
               i=220000;
            }
            System.out.println("Билет номер " + i + " через цикл for");
        }

        int ticketNumber = 200000;
        while (ticketNumber <= 235000 )
        {
            if (ticketNumber == 210001)
            {
                ticketNumber = 220000;
            }
            System.out.println("Билет номер " + ticketNumber + " через цикл while");
            ticketNumber++;
        }
    }
}
