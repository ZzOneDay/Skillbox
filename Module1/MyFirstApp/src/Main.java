import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            int answer = JOptionPane.showConfirmDialog(new Frame(),
                    "Здравствуйте, я Павел)\nПриятно познакомиться, Хотите увидеть мои нарабоки по курсу Java?",
                    "Title", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().browse(new URL("https://github.com/ZzOneDay/Etegram").toURI());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при открытие ссылки");
            e.printStackTrace();
        }
    }
}
