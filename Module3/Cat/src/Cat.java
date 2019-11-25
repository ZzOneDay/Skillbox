
public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double eatenFood;
    private static int count;

    private boolean catIsLive = true;
    private ColoringType type;

    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;

    public static final double eyesCount = 2;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = MIN_WEIGHT;
        maxWeight = MAX_WEIGHT;

        count += 1;

        type = ColoringType.BLACK;
    }

    public Cat (Double weight)
    {
        this();
        this.weight = weight;
        this.originWeight = weight;
    }

    public Cat getCloneThisCat ()
    {
        //Даже если кошка умерла, ее копию можно сделать, и копия будет тоже мертва.
        Cat cloneCat = new Cat(weight);
        cloneCat.checkLifeCat(weight);
        cloneCat.setColoringType(getColoringType());
        return cloneCat;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public void setColoringType (ColoringType type)
    {
        this.type = type;
    }

    public ColoringType getColoringType ()
    {
        return type;
    }



    public void meow() {
        if (catIsLive) {
            weight = weight - 1;
            System.out.println("Meow");
            checkLifeCat(weight);
        }
    }

    public void feed(Double amount) {
        if (catIsLive) {
            eatenFood += amount;
            weight = weight + amount;
            checkLifeCat(weight);
        }
    }

    public void drink(Double amount) {
        if (catIsLive) {
            eatenFood += amount;
            weight = weight + amount;
            checkLifeCat(weight);
        }
    }

    public void goToToilet() {
        if (catIsLive) {
            weight -= 0.05 * weight;
            System.out.println("Мяу-Мяу, Убери мой горшок, кожанный раб");
            checkLifeCat(weight);
        }
    }

    public Double getWeight() {
        return weight;
    }

    public Double getEatenFood() {
        return eatenFood;
    }

    public static int getCount() {
        return count;
    }


    public String getStatus() {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }


    private void checkLifeCat(Double weightCatNow) {
        if (weightCatNow > maxWeight) {
            setCatIsDead();
        }
        if (weightCatNow < minWeight) {
            setCatIsDead();
        }
    }

    private void setCatIsDead() {
        //Использовать только с условием catIsLive,
        //вызывается в Условии что кошка жива, Если без условия то может умирать несколько раз,
        //условие не прописывается здесь, чтобы не допустить кормления или мяуканься умершей кошки
        count -= 1;
        catIsLive = false;
    }
}