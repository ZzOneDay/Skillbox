import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Новиков Павел Александрович

        for (; ; ) {
            System.out.println("Напишите ваши данные: Фамилия Имя Отвество");
            Scanner scanner = new Scanner(System.in);
            String textByScanner = scanner.nextLine().trim();
            if (textByScanner.length() == 0) {
                System.out.println("Вы ничего не написали");
                continue;
            }

            //Проверим, не указано ли одно лишь слово
            textByScanner += " ";
            if (textByScanner.indexOf(" ") + 1 == textByScanner.length()) {
                System.out.println("Фамилия: " + textByScanner);
                continue;
            }

            //Очистим созданный ранее пробел
            String string = textByScanner.substring(0, textByScanner.lastIndexOf(" "));
            //Значит внутри есть пробел, отделим первую часть до пробела - фамилию
            String familyName = string.substring(0, string.indexOf(" ")).trim();
            //Напишем фамилию
            System.out.println("Фамилия: " + familyName);

            //Ищем дальше, строчка без фамилии
            String stringWithoutFamilyName = string.substring(string.indexOf(" ")).trim();
            //Добавляем пробел, для проверки условия, вдруг осталось лишь имя
            stringWithoutFamilyName += " ";
            if (stringWithoutFamilyName.indexOf(" ") + 1 == stringWithoutFamilyName.length()) {
                System.out.println("Имя: " + stringWithoutFamilyName);
                continue;
            }

            //Очистим созданный ранее пробел
            String stringFirstSecondName = stringWithoutFamilyName.substring(0, stringWithoutFamilyName.lastIndexOf(" "));
            //Значит внутри есть пробел, отделим часть до пробела - имя
            String firstName = stringFirstSecondName.substring(0, stringFirstSecondName.indexOf(" ")).trim();
            System.out.println("Имя: " + firstName);

            //Ищем дальше, строчка без Имени и Фамилии
            String stringWithoutFirstFamilyName = stringWithoutFamilyName.substring(stringWithoutFamilyName.indexOf(" ")).trim();
            //Добавляем пробел, для проверки условия, вдруг осталось лишь отчество
            stringWithoutFirstFamilyName += " ";
            if (stringWithoutFirstFamilyName.indexOf(" ") + 1 == stringWithoutFirstFamilyName.length()) {
                System.out.println("Отчество: " + stringWithoutFirstFamilyName);
                continue;
            }

            //Значит внутри есть пробел, отделим часть до пробела - Отчество, все остально это Дополнительно
            String secondName = stringWithoutFirstFamilyName.substring(0,stringWithoutFirstFamilyName.indexOf(" "));
            System.out.println("Отчество: " + secondName);

            String anyInfo = stringWithoutFirstFamilyName.substring(stringWithoutFirstFamilyName.indexOf(" ")).trim();
            System.out.println("Дополнительно: " + anyInfo);
            System.out.println("\n");
        }
    }
}

