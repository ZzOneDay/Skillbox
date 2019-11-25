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
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight ()
    {
        return height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setHasVehicle(boolean hasVehicle) {
        this.hasVehicle = hasVehicle;
    }

    public boolean getHasVehicle ()
    {
        return hasVehicle;
    }

    public void setIsSpecial(boolean special) {
        isSpecial = special;
    }

    public boolean getIsSpecial() {
        return isSpecial;
    }
}
