import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int countBox;

        int limContainerInTruck = 12;
        int limBoxInContainer = 27;

        //27*12=324

        int boxNumber = 0;
        int containerNumber = 0;
        int truckNumber = 0;

        for (;;) {
            try {
                System.out.println("Укажите необходимое кол-во ящик для транспортировки:");
                Scanner scanner = new Scanner(System.in);
                countBox = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Внимание! Следует вводить только целые числа например: 324");
            }
        }

        while (countBox > 0) {
            truckNumber++;
            System.out.println("Грузовик " + truckNumber + ":");
            for (int c = 0; c < limContainerInTruck; c++) {
                if (countBox > 0) {
                    containerNumber++;
                    System.out.println("\tКонтейнер " + containerNumber + ":");
                    for (int i = 0; i < limBoxInContainer; i++) {
                        if (countBox > 0) {
                            boxNumber++;
                            countBox--;
                            System.out.println("\t\tЯщик " + boxNumber);
                        }
                    }
                }
            }
        }
        int containersInLastTruck = containerNumber%limContainerInTruck;
        int percentLoadTruck = (int) (((double) containersInLastTruck / limContainerInTruck) * 100);
        if (percentLoadTruck == 0) {percentLoadTruck = 100;}

        System.out.println("Для размещения ящиков в кол-ве " + boxNumber + " шт." +
                "\nнеобходимо контейнеров в кол-ве " + containerNumber + " шт.\n" +
                "необходимо грузовых машин в кол-ве " + truckNumber + " шт.");
        System.out.println("последняя машина будет заполнена контейнерами на " + percentLoadTruck + "%");

    }
}
