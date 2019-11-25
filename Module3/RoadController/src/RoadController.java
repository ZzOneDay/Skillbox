import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    private static double passengerCarMaxWeight = 3500.0; // kg //Переменная типа double
    private static int passengerCarMaxHeight = 2000; // mm //Переменная типа int
    private static int controllerMaxHeight = 3500; // mm //Переменная типа int //23.07 4000->3500 mm

    private static int passengerCarPrice = 100; // RUB //Переменная типа int
    private static int cargoCarPrice = 250; // RUB //Переменная типа int
    private static int vehicleAdditionalPrice = 200; // RUB //Переменная типа int

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt(); //Переменная типа int

        for(int i = 0; i < carsCount; i++)
        {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Проверка на габарит любого автомобиля, даже спец транспорта
            if (car.getHeight() > controllerMaxHeight)
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue;
            }

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car); //Переменная типа int
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.height; //Переменная типа int
        int price = 0; //Переменная типа int

        if (carHeight > passengerCarMaxHeight)
        {
            double weight = car.weight; //Переменная типа double
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight)
            {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль //Была ошибка, ранее была cargoCarPrice
            else {
                price = passengerCarPrice;
            }
        }
        else {
            //Ранее была ошибка, ранее было passengerCarPrice
            price = cargoCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}