package core;

public class Car
{
    public String number; //Переменная типа String
    public int height; //Переменная типа int
    public double weight; //Переменная типа double
    public boolean hasVehicle; //Переменная типа boolean
    public boolean isSpecial; //Переменная типа boolean

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : ""; //Переменная класса String
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public int getHeight ()
    {
        return height;
    }
}