import java.util.Scanner; //Имплеменитируй класс Сканер, для определения что мы написали в терминале

//500917 минимальная инвестиция

public class Main //Обявляем класс
{
    private static int minIncome = 200000; //Минимальная инвестиция, граница
    private static int maxIncome = 900000; //Максимальная инвестиция, граница

    private static int officeRentCharge = 140000; //Оплата аренды офиса, комманульные платежи
    private static int telephonyCharge = 12000; //Оплата телефонов, коммунальные платежи
    private static int internetAccessCharge = 7200; //Оплата доступа в интернет, коммунальные платежи

    private static int assistantSalary = 45000; //Зарплата помощника
    private static int financeManagerSalary = 90000; //Зарплата финансового менаджера

    private static double mainTaxPercent = 0.24; //Процент налога
    private static double managerPercent = 0.15; //Процент менеджерам - продажникам

    private static double minInvestmentsAmount = 100000; //минимальная сумма сколько нужно отдать инвесторам(заработать)

    public static void main(String[] args) //Обвляем метод
    {
//        int income = 200000; //Если активировать эту строчку и ниже income++ и Условия ответа с break то найдем ответ.
//        и убрать int income = (new Scanner... - то получим расчет минимального требоваемого дохода.
        while(true) //начинаем цикл до break
        {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч рублей): ");
            //Пишет строчку в терминал до сканирования написания в терминале

            int income = (new Scanner(System.in)).nextInt();
            //определяем переменную целого числа из терминала, значение дохода

            if(!checkIncomeRange(income)) {
                continue;
                //Если не попали в рамки от 200 000 до 900 000 то цикл занова
                //Проверка, если !-Не, Выход за рамки-Нет, получается = Да.
            }

            double managerSalary = income * managerPercent;
            //Зарплата менаджеров - продажников, варируемая от % продаж * сумму дохода
            double pureIncome = income - managerSalary -
                calculateFixedCharges();
            //Чистый доход, Доход - ЗП Продажников - ЗП Фиксированные и Коммульные платежи
            double taxAmount = mainTaxPercent * pureIncome;
            //Расчет сколько придется отдать налогов
            double pureIncomeAfterTax = pureIncome - taxAmount;
            //СуперЧистаяПрибыльКомпании = Чистый доход - Налоги

            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;
            //Переменная boolean РентабельностьКомпании, значение которой, зависит от СуперЧистаяПрибыль больше
            // минимальной сумму сколько нужно отдать инвесторам, или нет

            System.out.println("Зарплата менеджера: " + managerSalary);
            //Пишет в консоль зарплата менеджеров продажников
            System.out.println("Общая сумма налогов: " +
                (taxAmount > 0 ? taxAmount : 0));
            //Пишет в консоль сумму налога, если она больше нуля то сумму налога, если меньше нуля то пишет НОЛЬ.
            System.out.println("Компания может инвестировать: " +
                (canMakeInvestments ? "да" : "нет"));
            //Пишет в консоль, если комания может инвестровать, то ДА, если нет, то Нет.
            //Зависит от условия РентабельностиКомпании
            if(pureIncome < 0) {
                //Если чистая прибыль равна >0, пишет собщени в консоль
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
            //Для Поиска минимального требуемого дохода для Ренательности компании
//            if (canMakeInvestments)
//            {
//                System.out.println("Готово, минимальная инвестиция это:" + income);
//                break;
//            }
//            income++;
        }
    }

    private static boolean checkIncomeRange(int income)
    {
        //Метод, если выходим за рамки возвращается false
        if(income < minIncome)
        //Проверка условия, доход меньше минимальной границы
        {
            System.out.println("Доход меньше нижней границы");
            //Сообщения в консоль
            return false;
            //закрывает метод и возвращает boolean при вызове метода
        }
        if(income > maxIncome)
        //Проверк условия, доход больше максимальной границы
        {
            System.out.println("Доход выше верхней границы");
            //Сообщения в консоль
            return false;
            //закрывает метод и возвращает boolean при вызове метода
        }
        return true;
        //Если за рамки не вышло, возращаем true - Правда
    }

    private static int calculateFixedCharges()
            //Статический метод, возвращающий целое число, коммунальные затраты + фиксированные ЗП
    {
        return officeRentCharge +
                telephonyCharge +
                internetAccessCharge +
                assistantSalary +
                financeManagerSalary;
        //Возвращается суммму всех этих числе
    }
}
