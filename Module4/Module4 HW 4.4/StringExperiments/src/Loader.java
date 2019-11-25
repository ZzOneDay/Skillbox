public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);

        //Разрезаем строки под каждого работника
        String vasyaPayString = text.substring(0, text.indexOf(','));
        String petyaPayString = text.substring(text.indexOf(','), text.lastIndexOf(','));
        String mashaPayString = text.substring(text.lastIndexOf(','));

        //Находим последни симвал суммы каждого сотрудника
        int vasiaLastCharPay = vasyaPayString.indexOf("руб");
        int petyaLastCharPay = petyaPayString.indexOf("руб");
        int mashaLastCharPay = mashaPayString.indexOf("руб");

        //Обрезаем строчку от начала до последнего симвала зарплаты сотрудника
        String vasiaClearLastString = vasyaPayString.substring(0, vasiaLastCharPay).trim();
        String peryaClearLastString = petyaPayString.substring(0, petyaLastCharPay).trim();
        String mashaClearLastString = mashaPayString.substring(0, mashaLastCharPay).trim();

        //Находим первый симвал суммы каждого сотрудника
        int vasiaFirstCharPay = vasiaClearLastString.lastIndexOf(" ");
        int petyaFirstCharPay = peryaClearLastString.lastIndexOf(" ");
        int mashaFirstCharPay = mashaClearLastString.lastIndexOf(" ");

        //Обрезаем уже обрезанноу строчку но уже по от первого симвала зарплаты
        String vasiaClearString = vasiaClearLastString.substring(vasiaFirstCharPay).trim();
        String peryaClearString = peryaClearLastString.substring(petyaFirstCharPay).trim();
        String mashaClearString = mashaClearLastString.substring(mashaFirstCharPay).trim();

        //Переводчим из Строчки в Число, зарплату каждого сотрдуника
        int vasiaHasMoney = Integer.parseInt(vasiaClearString);
        int petyaHasMoney = Integer.parseInt(peryaClearString);
        int mashHasMoney = Integer.parseInt(mashaClearString);

        //Выводим результат
        System.out.println("Сумма заработка Вася + Маша = " + (vasiaHasMoney + mashHasMoney));

        System.out.println("Сумма заработка Вася + Петя + Маша = "
                + (vasiaHasMoney + petyaHasMoney + mashHasMoney));

    }
}