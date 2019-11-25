public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        String[] fragments = text.split(",");

        int amountOfMoney = 0;

        for (int i = 0; i < fragments.length; i++) {
            String clearString = fragments[i].trim().replaceAll("[^0-9]+", "");
            amountOfMoney += Integer.parseInt(clearString);
        }

        System.out.println(text);
        System.out.println("Общая сумма заработка: " + amountOfMoney + " руб.");

    }
}