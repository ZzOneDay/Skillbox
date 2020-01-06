import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHTML {
    private ArrayList<String> ListImagesPath;

    ParseHTML (String pathHtml) {
        ListImagesPath = getImagesPaths(pathHtml);
    }

    private ArrayList<String> getImagesPaths (String pathHtml) {
        ArrayList<String> imagesPaths = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect(pathHtml).get();
            Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            String baseUri = images.get(0).baseUri();
            images.forEach(image -> imagesPaths.add(image.attr("src")));
            for (int i = 0; i < imagesPaths.size(); i++) {
                if (imagesPaths.get(i).charAt(0) == '/') {
                    imagesPaths.set(i,baseUri + imagesPaths.get(i));
                }
            }
            imagesPaths.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Ошибка при открытие страницы");
            e.printStackTrace();
        }
        return imagesPaths;
    }

    public void saveAllImages (String pathDir) {
        try {
            for (String path : ListImagesPath) {
                InputStream inputStream = new URL(path).openStream();
                FileOutputStream fileOutputStream = new FileOutputStream(getCorrectPath(pathDir,path));
                fileOutputStream.write(inputStream.readAllBytes());
                fileOutputStream.close();
                System.out.println("Image: " + path + "  - save");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCorrectPath (String pathDir, String pathImage) {
        String path = pathDir;
        Pattern r = Pattern.compile("([0-9A-Za-z\\-_]+\\.(png|jpe?g|gif))");
        Matcher m = r.matcher(pathImage);
        if (m.find()) {
            path +="/" +pathImage.substring(m.start());
        }
        return path;
    }

    public void saveAllImages () {
        saveAllImages("Date");
    }
}
