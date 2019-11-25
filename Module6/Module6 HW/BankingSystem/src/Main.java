import Сustomer.Client;
import Сustomer.Entity;
import Сustomer.Individual;
import Сustomer.IndividualEntrepreneur;

public class Main {
    public static void main(String[] args) {
        Client individual = new Individual();
        individual.printBalanceAccount();
        individual.addMoneyToAccount(100.0);
        individual.printBalanceAccount();
        individual.withdrawMoneyByAccount(50.0);
        individual.printBalanceAccount();
        individual.withdrawMoneyByAccount(10000.0);

        System.out.println("===============================");

        Client entity = new Entity();
        entity.printBalanceAccount();
        entity.addMoneyToAccount(800.0);
        entity.printBalanceAccount();
        entity.withdrawMoneyByAccount(200.0);
        entity.printBalanceAccount();

        System.out.println("===============================");

        Client individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.printBalanceAccount();
        individualEntrepreneur.addMoneyToAccount(800.0);
        individualEntrepreneur.printBalanceAccount();
        individualEntrepreneur.withdrawMoneyByAccount(200.0);
        individualEntrepreneur.printBalanceAccount();
        individualEntrepreneur.addMoneyToAccount(999.9);
        individualEntrepreneur.addMoneyToAccount(100.0);
        individualEntrepreneur.addMoneyToAccount(1000.0);

    }
}
