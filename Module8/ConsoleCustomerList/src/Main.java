import java.util.Scanner;

public class Main
{
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);
            if (tokens[0].equals("add")) {
                try {
                    executor.addCustomer(tokens[1]);
                }
                catch (IndexOutOfBoundsException ex)
                {
//                    ex.printStackTrace();
                    System.out.println("Неправильная работа с командой ADD (" + ex.getMessage() + ")\nПример: " + addCommand);
                }
            }
            else if(tokens[0].equals("list")) {
                try {
                    executor.listCustomers();
                }
                catch (IndexOutOfBoundsException ex)
                {
                    System.out.println("Неправильная работа с командой list (" + ex.getMessage() + ")");
                }
            }
            else if(tokens[0].equals("remove"))
            {
                try {
                    executor.removeCustomer(tokens[1]);
                }
                catch (ArrayIndexOutOfBoundsException ex)
                {
                    System.out.println("Неправильная работа с командой remove");

                }
                catch (Exception ex)
                {
                    System.out.println("Неправильная работа с командой remove (" + ex.getMessage() + ")");
                }
            }
            else if(tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
            }
            else if(tokens[0].equals("help")) {
                System.out.println(helpText);
            }
            else {
                System.out.println(commandError);
            }
        }
    }
}
