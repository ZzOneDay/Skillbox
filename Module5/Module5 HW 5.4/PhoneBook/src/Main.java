import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static TreeMap<String, String> phoneBook = new TreeMap<>(); // String name, String number; Ключ - Имя, Знач - Номер

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Поиск/Добавления по phoneBook, укажите номер телефона или Имя контакта");


        for (; ; ) {
            String command = scanner.nextLine();
            command = clearCommandString(command);
            if (command.equals("LIST")) {
                if (phoneBook.size() != 0) {
                    printMap(phoneBook);
                } else {
                    System.out.println("Список контактов - пуст");
                }
                continue;
            }
            if (stringIsName(command) && phoneBookHasThisName(command))
            //Это строчка с именем, Это имя есть в базе
            {
                System.out.println("Найден контакт\nимя:\t" + command + "\nтел:\t" + phoneBook.get(command));
                continue;
            }
            if (stringIsName(command) && !phoneBookHasThisName(command))
            //Это строчка с именем, но имени нет в базе
            {
                System.out.println("Укажите номер телефона, нового контакта " + command);
                String phone = clearCommandString(scanner.nextLine());
                phoneBook.put(command, phone);
                System.out.println("Добавлен контакт: " + command + " " + phone);
                continue;
            }
            if (!stringIsName(command) && phoneBookHasThisNumber(command))
            //Это не Имя - Телефон, есть в базе
            {
                for (String name : phoneBook.keySet()) {
                    Pattern p = Pattern.compile(command);
                    Matcher m = p.matcher(phoneBook.get(name));
                    if (m.find()) {
                        System.out.println("Найден контакт\nимя:\t" + name + "\nтел:\t" + phoneBook.get(name));
                        break;
                    }
                }
                continue;
            }
            if (!stringIsName(command) && !phoneBookHasThisName(command))
            //Это не Имя - Телефон, нет в базе
            {
                System.out.println("Укажите имя контакт, телефона " + command);
                String name = clearCommandString(scanner.nextLine());
                if (phoneBook.containsKey(name)) {
                    addAdditionalNumberToPhoneBook(name, command);
                } else {
                    phoneBook.put(name, command);
                    System.out.println("Добавлен контакт: " + name + " " + command);
                }
            }
        }
    }

    static private boolean stringIsName(String command) {
        boolean stringIsName = false;
        Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+");
        Matcher m = p.matcher(command);
        if (m.find()) {
            stringIsName = true;
        }
        return stringIsName;
    }

    static private boolean phoneBookHasThisName(String name) {
        boolean phoneBookHaveThisName = false;
        if (phoneBook.containsKey(name)) {
            phoneBookHaveThisName = true;
        }
        return phoneBookHaveThisName;

    }

    static private boolean phoneBookHasThisNumber(String number) {
        boolean phoneBookHasThisNumber = false;

        for (String name : phoneBook.keySet()) {
            Pattern p = Pattern.compile(number);
            Matcher m = p.matcher(phoneBook.get(name));
            if (m.find()) {
                phoneBookHasThisNumber = true;
                break;
            }
        }
        return phoneBookHasThisNumber;
    }

    static private String clearCommandString(String command) {
        if (stringIsName(command)) {
            command = command.toUpperCase().trim();
        }
        if (!stringIsName(command)) {
            command = command.replaceAll("[^0-9]+", "");
            if (command.substring(0, 1).equals("7")) {
                command = 8 + command.substring(1);
            }
        }
        return command;
    }

    static private void printMap(Map<String, String> map) {
        System.out.println("Список контактов:");
        for (String name : map.keySet())
            System.out.println(name + " " + map.get(name));
    }

    static private void addAdditionalNumberToPhoneBook(String name, String additionalNumber) {
        String number = phoneBook.get(name);
        number += "; " + additionalNumber;
        phoneBook.put(name, number);
        System.out.println("К контакту " + name + " добавлен номер: " + additionalNumber);
    }
}
