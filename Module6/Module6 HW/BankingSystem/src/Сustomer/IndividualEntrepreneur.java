package Сustomer;

public class IndividualEntrepreneur extends Client {
    //Индивидуальный предприниматель

    @Override
    public void addMoneyToAccount(Double amount) {
        double commission;
        double amountToAddMoneyAccount;
        double perCommission = amount > 1000 ? 0.01 : 0.005;

        commission = amount * perCommission;
        commission = Math.ceil(commission); //Округляем в сторону прибыли банка, убытка клиента
        amountToAddMoneyAccount = amount - commission;
        System.out.println("Получено на внесение: " + amount);
        System.out.println("Комисиия за внесение: " + commission);
        System.out.println("На счет добавлено: " + amount);
        super.addMoneyToAccount(amountToAddMoneyAccount);
    }
}
