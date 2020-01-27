package MetroCore.Components;

public class Station {


    private String name;
    private String numberLine;

    public Station(String numberLine, String name) {
        this.name = name;
        this.numberLine = numberLine;
    }

    public String getName() {
        return name;
    }

    public String getNumberLine() {
        return numberLine;
    }
}
