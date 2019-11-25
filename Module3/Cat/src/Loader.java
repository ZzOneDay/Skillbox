
public class Loader
{
    public static void main(String[] args)
    {
        Cat murzik = new Cat();
        Cat vasia = new Cat();
        Cat missa = new Cat();
        Cat liska = new Cat();

        System.out.println("Количество кошек: " + Cat.getCount()); //4 Кошки
        System.out.println("Murzik: " + murzik.getStatus()); //Мурзик жив

        for (int i = 0; i < 100000; i++)
        {
            murzik.meow();
        }
        System.out.println("Количество кошек: " + Cat.getCount()); //3 Кошки
        System.out.println("Murzik: " + murzik.getStatus()); //Мурзик мертв

        murzik.feed(10000.0); //Кормим труп

        System.out.println("Murzik: " + murzik.getStatus()); //Сколько мурзика не корми, он не воскреснет
        System.out.println("Количество кошек: " + Cat.getCount()); //3 кошки, никто не ожил



        System.out.println("Vasia: " + vasia.getStatus()); //жив
        System.out.println("Missa: " + missa.getStatus()); //жив
        System.out.println("liska: " + liska.getStatus()); //жив

        vasia.drink(10000.0); //Кормим васю до смерти

        missa.feed(10.0); //чуть чуть кормим миссу
        missa.drink(110.0); //чуть чуть кормим миссу
        System.out.println("Missa: " + missa.getEatenFood()); //Сколько сьела еды 120
        missa.meow(); //Мисса мяукает
        System.out.println("Missa: " + missa.getEatenFood()); //После мяуканья кол-во сьеденной еды не изменилось = 120



        System.out.println("Murzik: " + murzik.getStatus()); //Перемяукал
        System.out.println("Vasia: " + vasia.getStatus()); //Переел
        System.out.println("Missa: " + missa.getStatus()); //Покушала и спит

        System.out.println("liska: " + liska.getWeight()); //Лиска вес = Ок
        liska.goToToilet(); //Сходила в туалет % от Массы
        System.out.println("liska: " + liska.getWeight()); //Лиска вес изменился после похода в туалет


        liska.setColoringType(ColoringType.WHITE); //Миска задали цвет белый
        System.out.println("Liska какой у тебя цвет? " + liska.getColoringType());

        System.out.println(Cat.getCount()); //Колличество кошек 2
        System.out.println(getNewCat(2000.0).getWeight()); //Создаем кошку со спец весом
        System.out.println(getNewCat(2500.0).getWeight()); //Создаем кошку со спец весом
        System.out.println(Cat.getCount()); //Количество кошек 4
        Cat cat = getNewCat(2000.0); //Создаем нового кота весом 2000
        System.out.println("Вес подопытной кошки(оригинал) = " + cat.getWeight());

        System.out.println("Вес Оригинал " + cat.getWeight() + "; Состояние Оригинал " + cat.getStatus());
        System.out.println("Количество кошек до Клонирования " + Cat.getCount());
        Cat cloneCat = cat.getCloneThisCat();
        System.out.println("Количество кошек после Клонирования " + Cat.getCount());
        System.out.println("Вес клона " + cloneCat.getWeight() + "; Состояние Клона " + cloneCat.getStatus());
        System.out.println("Глубоко клоннирование прошло успешно");

        System.out.println("Проверка клона");
        System.out.println("Вес сейчас " + cloneCat.getWeight() + "; На момент создания " + cloneCat.getOriginWeight());
        cloneCat.meow();
        System.out.println("Вес сейчас " + cloneCat.getWeight() + "; На момент создания " + cloneCat.getOriginWeight());


    }

    private static Cat getNewCat (Double weight)
    {
        return new Cat(weight);
    }
}