import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static private String patternCommandAdd = "^ADD\\s[А-Яа-яA-Za-z]";
    static private String patternCommandAddIndex = "^ADD\\s\\d+\\s[А-Яа-яA-Za-z]";
    static private String patternCommandEditIndex = "^EDIT\\s\\d+\\s[А-Яа-яA-Za-z]";
    static private String patternCommandDeleteIndex = "^DELETE\\s\\d+";
    static private String patternCommandList = "LIST";
    static private String patternCommandHELP = "HELP";

    static private ArrayList<String> toDoList = new ArrayList<>();


    public static void main(String[] args) {

        System.out.println("Программа для записи списка дела, получить инструкцию можно командой HELP");

        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            String commandByScanner = scanner.nextLine();
            if (getMatherToString(commandByScanner, patternCommandHELP)) {
                printHelp();
            } else {
                setChangeToArrayList(commandByScanner);
            }
        }
    }


    static private void setChangeToArrayList(String command) {
        //Определяем какая команда пришла из консоли, и выполняем действия со списком
        if (getMatherToString(command, patternCommandList)) {
            //Распечатать список заданий
            printToDoList();
        } else if (getMatherToString(command, patternCommandAdd)) {
            //Добавить задание
            addToArrayList(command, patternCommandAdd);
            System.out.println("patternCommandAdd");
        } else if (getMatherToString(command, patternCommandAddIndex)) {
            //Добавить задание по Индексу
            AddToArrayListByIndex(command, patternCommandAddIndex);
            System.out.println("patternCommandAddIndex");
        } else if (getMatherToString(command, patternCommandEditIndex)) {
            //Изменить задание по Индексу
            editToArrayListByIndex(command, patternCommandEditIndex);
            System.out.println("patternCommandEditIndex");
        } else if (getMatherToString(command, patternCommandDeleteIndex)) {
            //Удалить задание по Индексу
            deleteToArrayListByIndex(command, patternCommandDeleteIndex);
            System.out.println("patternCommandDeleteIndex");
        } else {
            //Указана не верная команда
            System.out.println("Вы указали не верную команду, попробуйте еще раз");
        }

    }

    static private boolean getMatherToString(String string, String pattern) {
        //Проверяем какая именно это команда
        boolean check = false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        if (m.find()) {
            check = true;
        }
        return check;
    }


    //Методы по работе со список по команде
    static private void addToArrayList(String command, String pattern) {
        String clearString = textThisCommand(command, pattern);

        toDoList.add(clearString);
    }

    static private void AddToArrayListByIndex(String command, String pattern) {
        String clearString = textThisCommand(command, pattern);
        int indexTask = numberIndexThisCommand(command)-1;//+1 Избавляемся от задание 0

        if (indexTask < toDoList.size()) {
            toDoList.add(indexTask, clearString);
        } else {
            System.out.println(indexTask + " отсутствует, задание добавлено в конец списка");
            toDoList.add(clearString);
        }
    }

    static private void editToArrayListByIndex(String command, String pattern) {
        String clearString = textThisCommand(command, pattern);
        int indexTask = numberIndexThisCommand(command)-1;

        if (indexTask < toDoList.size()) {
            toDoList.add(indexTask, clearString);
            toDoList.remove(indexTask + 1);
        } else {
            System.out.println("Задание номер " + indexTask+1 + " отсутствует");
        }
    }

    static private void deleteToArrayListByIndex(String command, String pattern) {
        //pattern не используется
        int indexTask = numberIndexThisCommand(command)-1;
        if (indexTask < toDoList.size()) {
            toDoList.remove(indexTask);
        } else {
            System.out.println("Задание номер " + indexTask+1 + " отсутствует");
        }
    }

    //Распечатать список дел
    static private void printToDoList() {
        if (toDoList.size() == 0)
        {
            System.out.println("Список дел пуст");
        }
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i+1 + " " + toDoList.get(i));
        }
    }

    //Распечатать список команда
    static private void printHelp() {
        System.out.println("Инструкция по использовани программы toDoList" +
                "\nДобавлять, Редактировать, Удалять дела, посредством комманд: " +
                "\n\tADD,ADD Index, EDIT Index, DELETE Index" +
                "\n\tLIST - Распечатать список делал" +
                "\nНапример: ADD Сходить в магазин; EDIT 1 Заказать еду; DELETE 4");
    }


    //Получаем чистый текст задания из команды
    static private String textThisCommand(String command, String pattern) {
        String clearText = "";
        if (pattern.equals(patternCommandAdd)) {
            //ADD Text
            clearText = command.substring(command.indexOf(" ") + 1);

        }
        if (pattern.equals(patternCommandAddIndex)) {
            //ADD 0 Text
            clearText = command.substring(command.indexOf(" "));
            clearText = clearText.trim();
            clearText = clearText.substring(clearText.indexOf(" "));
            System.out.println(clearText);

        }
        if (pattern.equals(patternCommandEditIndex)) {
            //EDIT 0 Text
            clearText = command.substring(command.indexOf(" "));
            clearText = clearText.trim();
            clearText = clearText.substring(clearText.indexOf(" "));
        }
        return clearText.trim();
    }

    //Получаем номер индекса из команды
    static private int numberIndexThisCommand(String command) {
        int numberIndex;
        String string = command.substring(command.indexOf(" ")).trim();
        string += " ";
        numberIndex = Integer.parseInt(string.substring(0, string.indexOf(" ")));
        if (numberIndex == 0)
        {
            numberIndex = 1;
        }
        return numberIndex;
    }


}
