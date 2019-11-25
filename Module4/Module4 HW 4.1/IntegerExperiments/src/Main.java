public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;

        System.out.println(sumDigits(container.count));
    }

    //Реализуйте метод sumDigits так, чтобы он считал сумму цифр в числе,
    //используя метод Integer.toString и методы строки charAt() и length().

    public static Integer sumDigits(Integer number) {
        //@TODO: write code here
        String numberToString = number.toString();
        int amount = 0;
        for (int i = 0; i < numberToString.length(); i++)
        {
            amount += Character.getNumericValue(numberToString.charAt(i));
        }
        return number.toString().length();
    }
}
