import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> numbersList = new ArrayList<>();

        char[] chars = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'Y'};
        int per;

        //Генерация номер
        for (int chA = 0; chA < chars.length; chA++) {
            for (int chB = 0; chB < chars.length; chB++) {
                for (int number = 111; number <= 999; number++) {
                    for (int chC = 0; chC < chars.length; chC++) {
                        for (int region = 1; region <= 300; region++) {
                            String numbers = Integer.toString(number);
                            String regions = Integer.toString(region);
//                            if (region < 10) {
//                                regions = "0" + region; //00
//                            }
//                            if (region < 100 && region >= 10)
//                            {
//                                regions = "0" + region;
//                            }
                            String coolNumber = numbers.substring(0,1) + numbers.substring(0,1) + numbers.substring(0,1);
                            if (numbers.equals(coolNumber)) {
//                                System.out.println(chars[chA] + numbers + chars[chB] + chars[chC] + regions + "");
                                numbersList.add(chars[chA] + numbers + chars[chB] + chars[chC] + regions);
                            }
                            if (numbersList.size()%50000==0)
                            {
                                per = (int)(((double) numbersList.size() / 4630000) * 100);
                                System.out.print("Генерация номеров:" + per + "%\r");
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(numbersList);

        TreeSet<String> numbersTreeSet = new TreeSet<>(numbersList);
        HashSet<String> numbersHashSet = new HashSet<>(numbersList);
        System.out.println("Генерация номеров законена, в базе: " + numbersList.size() + " номеров");
        System.out.println(numbersList.size() + ";" + numbersTreeSet.size() + ";" + numbersHashSet.size());


        Scanner scanner = new Scanner(System.in);
        for (;;)
        {
            System.out.println("Введите номер для поиска, например A444AB98");
            String numberToFind = scanner.nextLine();
            boolean listHasThisNumber = false;


            long timeFindListToOrder = 0;
            long timeFindListToBinary = 0;
            long timeFindTreeSet = 0;
            long timeFindHashSet = 0;
            long start = System.nanoTime();
            if (numbersList.contains(numberToFind))
            {
                timeFindListToOrder = System.nanoTime() - start;
                listHasThisNumber = true;
            }
            start = System.nanoTime(); //Обнуляем
            if (Collections.binarySearch(numbersList,numberToFind) != -1)
            {
                timeFindListToBinary = System.nanoTime() - start;
            }
            start = System.nanoTime(); //Обнуляем
            if (numbersTreeSet.contains(numberToFind))
            {
                timeFindTreeSet = System.nanoTime() - start;
            }
            start = System.nanoTime(); //Обнуляем
            if (numbersHashSet.contains(numberToFind))
            {
                timeFindHashSet = System.nanoTime() - start;
            }
            if(listHasThisNumber) {
                System.out.println("Перебором: " + timeFindListToOrder + " ns");
                System.out.println("Бинарный: " + timeFindListToBinary + " ns");
                System.out.println("По TreeSet: " + timeFindTreeSet + " ns");
                System.out.println("По HashSet: " + timeFindHashSet + " ns");
            }
            else
                {
                    System.out.println("Номер: " + numberToFind + " в базе не найден");
                }
        }
    }
}
