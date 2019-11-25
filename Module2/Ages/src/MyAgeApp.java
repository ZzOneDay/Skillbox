import java.util.TreeSet;

class MyAgeApp {

    private String []fragments;

    /**
     *Я сначала даже сделал чтобы в конструктор задавалось множество числен, массив Integer'ов,
     * И легко считает максиму и минимум,
     * высчитывает среднее, и количество шагов до ближайшшего от среднего вверх, и до ближайшего среднего вниз,
     * проверяет что ближе и выдает ответ как средний возраст из массива чисел, но подумал, чего вас грузить,
     * и решил дальше курс смотреть, циклы на подходе
     */




    MyAgeApp(Integer player1, Integer player2, Integer player3) {
        TreeSet<Integer> players = new TreeSet<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        String string = players.toString();
        String clearString = string.replaceAll("[^0-9\\s]+", "");
        fragments = clearString.split((" "));
    }


    String getStringLevelAge() {
        String stringAgeLevel = "";
        int max;
        int midl;
        int min;
        if (fragments.length == 3) {
            min = Integer.parseInt(fragments[0]);
            midl = Integer.parseInt(fragments[1]);
            max = Integer.parseInt(fragments[2]);
            stringAgeLevel = "min " + min + "; midl " + midl + "; max " + max;
        }
        if (fragments.length == 2) {
            min = Integer.parseInt(fragments[0]);
            max = Integer.parseInt(fragments[1]);
            stringAgeLevel = "min " + min + " max " + max;
        }

        if (fragments.length == 1) {
            min = Integer.parseInt(fragments[0]);
            stringAgeLevel = "min " + min + "; max " + min;
        }
        return stringAgeLevel;
    }
}
