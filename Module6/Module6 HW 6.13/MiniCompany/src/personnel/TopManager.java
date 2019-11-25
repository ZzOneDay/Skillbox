package personnel;

import comp.Company;

public class TopManager implements Employee {

    private String position = "TopManager";
    private Double salary = 45000.0;

    private Double salaryTopManager = 0.0;
    private Double premium = 15000.0; //Если прибыль компании больше 1 000 000

    private Company company; //В какой компании работает

    private double revenue = 100000;

    public TopManager(Company company) {
        this.company = company;
        company.addToProfit(revenue);
    }

    @Override
    public double getMonthSalary() {
        salaryTopManager = salary;
        if (company.getProfit() > 1000000) {
            salaryTopManager += premium;
        }

        return salaryTopManager;
    }

    @Override
    public String getJobTitle() {
        return position;
    }

    @Override
    public double revenue() {
        return revenue;
    }
}
