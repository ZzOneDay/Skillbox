import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        for (; ; ) {
            System.out.println("Укажите номер телефона в любом формате:");
            Scanner scanner = new Scanner(System.in);
            String number = scanner.nextLine();
            String cleanNumber = number.replaceAll("[^0-9]", "");
            System.out.println(cleanNumber);

        }
    }
}
