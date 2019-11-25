import comp.Company;
import personnel.Operator;
import personnel.SalaryComparator;
import personnel.SalesManager;
import personnel.TopManager;

public class Main {

    public static void main(String[] args) {
        Company company = new Company();

        TopManager topManager = new TopManager(company);
        SalesManager salesManager = new SalesManager(company);
        Operator operator = new Operator(company);

        company.hireEmployee(topManager);
        company.hireEmployee(salesManager);
        company.hireEmployee(operator);


        System.out.println("Прибыль компании: " + company.getProfit());
        System.out.println("Выручка компании: " + company.getRevenueCompany());


        company.dismissEmployee(operator);

        System.out.println("Прибыль компании: " + company.getProfit());
        System.out.println("Выручка компании: " + company.getRevenueCompany());

        company.dismissEmployee(salesManager);

        System.out.println("Прибыль компании: " + company.getProfit());
        System.out.println("Выручка компании: " + company.getRevenueCompany());

        company.dismissEmployee(topManager);

        System.out.println("Прибыль компании: " + company.getProfit());
        System.out.println("Выручка компании: " + company.getRevenueCompany());

        System.out.println("\nМассовый найм");


        for (int i = 0; i < 15; i++) {
            company.hireEmployee(new TopManager(company));
        }
        for (int i = 0; i < 55; i++) {
            company.hireEmployee(new SalesManager(company));
        }
        for (int i = 0; i < 200; i++) {
            company.hireEmployee(new Operator(company));
        }

//        System.out.println("Прибыль компании: ~" + company.getProfit());
        System.out.println("В компании работает: " + company.numberOfEmployees());
        System.out.println("-------------------------");
        System.out.println("Самые высокие заработки");
        company.getTopSalaryStaff(30);
        System.out.println("-------------------------");
        System.out.println("Самые низкие заработки");
        company.getLowestSalaryStaff(30);




    }
}
