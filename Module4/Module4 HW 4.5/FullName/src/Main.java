import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Нужны проверки вводимой строки (попробуйте реализовать с помощью одной регулярки):
//
//        количество слов 3-4 (учитываем двойные отчества "Ахмед оглы")
//        первые три слова - с большой буквы
//        все остальные буквы - маленькие
//        нет знаков препинания и прочих символов, за исключением тире (двойные имена и фамилии, например Салтыков-Щедрин)

public class Main {
    public static void main(String[] args) {
        //Новиков Павел Александрович

        String pattern = "^[А-Я][а-я]*(-[А-Я][а-я]*)?\\s[А-Я][а-я]*(-[А-Я][а-я]*)?\\s[А-Я][а-я]*(\\s[а-я]*)?$";

        Pattern p = Pattern.compile(pattern);

        System.out.println("Напишите ваши данные: Фамилия Имя Отвество");
        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String textByScanner = scanner.nextLine().trim();

            Matcher m = p.matcher(textByScanner);
            if (!m.find()) {
                System.out.println("Некорретный ввод!Пример:" +
                        "\n\tНовиков Павел Александрович" +
                        "\n\tСалтыков-Щедрин Павел-Котик Ахмед оглы");
                continue;
            }

            String[] fragments = textByScanner.split("\\s+");
            if (fragments.length == 3) {
                System.out.println("Фамилия: " + fragments[0]);
                System.out.println("Имя: " + fragments[1]);
                System.out.println("Отчество: " + fragments[2]);
            } else if (fragments.length == 4) {
                System.out.println("Фамилия: " + fragments[0]);
                System.out.println("Имя: " + fragments[1]);
                System.out.println("Отчество: " + fragments[2] + " " + fragments[3]);
            } else System.out.println("Не корректный ввод, попробуйте еще раз");
        }
    }
}

