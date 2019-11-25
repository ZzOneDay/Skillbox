import java.io.File;

public class Main {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\User\\Desktop\\javaSkillBox");

        SizeCounter sizeCounter = new SizeCounter(folder);
        try {
        System.out.println(sizeCounter.getInfoAboutFolder());
            int i = 0;
            while (folder.isDirectory()) {
                System.out.println(sizeCounter.getInfoAboutFiles(i));
                i++;
                if (i == sizeCounter.getCountFilesInFolder()) {
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("В работе программы произошла ошибка: " + e.getMessage());
            System.out.println("Проверьте корректность введеных данных (путь), или ваша папка пуста");
        }
    }
}
