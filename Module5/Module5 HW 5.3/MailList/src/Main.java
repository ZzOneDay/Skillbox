import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        "Abc\@def"@example.com
//        "Fred Bloggs"@example.com
//        "Joe\\Blow"@example.com
//        Abc@def"@example.com
//        customer/department=shipping@example.com
//        \$A12345@example.com
//        !def!xyz%abc@example.com
//        _somename@example.com
//        name.surname@gmail.com


        HashSet<String> mailList = new HashSet<>();
        String localPatternMail = "^ADD\\s[\"\\\\A-Za-z0-9$!_%@=/`#'{}^\\-&]" +
                "+(\\s?\\.?[\"\\\\A-Za-z0-9$!_%@=/`#'{}^\\-&]+)+?";
        String domainPatternMail = "@[a-zA-Z0-9.]+(-?_?[a-zA-Z0-9.]+)*?\\.[a-zA-Z]+(\\.[a-zA-Z]+)?";
        String patternMail = localPatternMail + domainPatternMail;


        System.out.println("Список адресов почты, с формате \"azminseo@gmail.com\"" +
                "\n\tADD - Добавить новый e-mail, LIST - Получить список e-mail адресов" +
                "\n\tНапример: ADD azminseo@gmail.com");
        for (; ; ) {
            System.out.println("Введите команду:");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim();

            if (command.equals("LIST")) {
                printMailList(mailList);
                continue;
            }
            Pattern p = Pattern.compile(patternMail);
            Matcher m = p.matcher(command);
            if (m.find() && getCheckCorrectMail(clearCommandToMail(command))) {
                command = clearCommandToMail(command).toLowerCase();
                mailList.add(command);
                System.out.println("\tДобавлен e-mail: " + command);

            } else {
                System.out.println("Не корректный ввод");
            }
        }
    }

    static private void printMailList(HashSet<String> mailList) {
        if (mailList.size() == 0) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Список e-mail адресов:");
            for (String mail : mailList) {
                System.out.println("\t" + mail);
            }
        }
    }

    static private String clearCommandToMail(String command) {
        String clearMain = command.substring(command.indexOf(" "));
        return clearMain.toLowerCase().trim();
    }

    static private boolean getCheckCorrectMail(String clearMail) {
        boolean stringIsOk = false;

        boolean stringHaveSpace;
        boolean stringHaveQuotes;

        String local = clearMail.substring(0, clearMail.lastIndexOf('@'));

        Pattern space = Pattern.compile("\\s+");
        Pattern quotes = Pattern.compile("[\"]");
        Matcher s = space.matcher(local);
        Matcher q = quotes.matcher(local);
        stringHaveSpace = s.find();
//        System.out.println("stringHaveSpace " + stringHaveSpace);
        stringHaveQuotes = q.find();
//        System.out.println("stringHaveQuotes " + stringHaveQuotes);

        if (stringHaveQuotes && stringHaveSpace) {
            int firstQuotes = clearMail.indexOf('"');
            int secondQuotes = clearMail.lastIndexOf('"');
            int spaceIndex = s.start();
            if (firstQuotes < spaceIndex && spaceIndex < secondQuotes) {
                stringIsOk = true;
            }
        }
        if (!stringHaveSpace) {
            stringIsOk = true;
        }
        return stringIsOk;
    }
}
