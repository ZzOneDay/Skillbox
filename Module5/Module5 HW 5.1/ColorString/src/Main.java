public class Main {

//    Повторить создание массива и заполнение цветами радуги,
//    как показано в видео, а затем написать код, переворачивающий этот массив.

    public static void main(String[] args) {

        String text = "Каждый охотник желает знать, где сидит фазан";

        String []colors =  text.split(",?\\s+");

        upendArrayStrings(colors);

        for (int i = 0; i < colors.length; i++)
        {
            System.out.println(colors[i]);
        }

    }


    private static void upendArrayStrings (String[] strings)
    {
        int lengthArray = strings.length;
        int lastElement = lengthArray - 1;

        for (int i = 0; i<(lengthArray/2);i++)
        {
            String firstString = strings[i];
            String lastString = strings[lastElement - i];
            strings[i] = lastString;
            strings[lastElement - i] = firstString;
        }

    }
}
