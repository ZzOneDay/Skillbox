import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderTransactionList
{
    public ArrayList<String []> getInformation (String path)
    {
        ArrayList<String[]> information = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                if (line.equals(lines.get(0))) {
                    continue;
                }
                line = getCorrectString(line);
                String [] infoArray = line.split(",");
                if (infoArray.length != 8) {
                    throw new Exception("не равно 8");
                }
                information.add(infoArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return information;
    };

    private String getCorrectString (String line) {
        while (Pattern.compile("\"").matcher(line).find()) {
            Pattern r = Pattern.compile("\"[0-9]+,[0-9]+\"");
            Matcher m = r.matcher(line);
            if (m.find()) {
                String startPart = line.substring(0, m.start());
                String needToCorrectPart = line.substring(m.start() + 1, m.end() - 1).replaceAll(",", ".");
                String lastPart = line.substring(m.end());
                line = startPart + needToCorrectPart + lastPart;
            }
        }
        return line;
    }
}
