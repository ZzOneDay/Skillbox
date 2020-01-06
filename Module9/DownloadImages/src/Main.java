public class Main {

    public static void main(String[] args) {
        ParseHTML parseHTML = new ParseHTML("https://lenta.ru/");
        parseHTML.saveAllImages();
    }
}


