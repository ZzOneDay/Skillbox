package Сustomer;

public class Entity extends Client {
    //Юридическре лицо

    @Override
    public void withdrawMoneyByAccount(Double amount) {
        double commission = amount * 0.01; // 1%
        if (getAmountMoneyInAccount() > (amount + commission)) {
            System.out.println("Снятие суммы: " + amount);
            System.out.println("Комиссия за снятие: " + commission);
            System.out.println("Со счет списано: " + (amount + commission));
            super.withdrawMoneyByAccount(amount + commission);
        } else {
            System.out.println("Недостаточно средств. (Попытка снять: " + amount + ")");
        }
    }
}
