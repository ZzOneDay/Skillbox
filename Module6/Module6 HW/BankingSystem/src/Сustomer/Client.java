package Сustomer;

abstract public class Client {
    private double amountMoneyInAccount;

    public void printBalanceAccount() {
        System.out.println("На счете: " + amountMoneyInAccount);
    }

    public void addMoneyToAccount(Double amount) {
        amountMoneyInAccount += amount;
        System.out.println("На счет добавлено: " + amount);
    }

    public void withdrawMoneyByAccount(Double amount) {
        if (amountMoneyInAccount > amount) {
            amountMoneyInAccount -= amount;
            System.out.println("Со счет списано: " + amount);
        } else {
            System.out.println("Недостаточно средств. (Попытка снять: " + amount + ")");
        }
    }

    //Доступ только в пакетдже
    double getAmountMoneyInAccount() {
        return amountMoneyInAccount;
    }
}
