package personnel;

import comp.Company;

public class SalesManager implements Employee {

    private String position = "SalesManager";
    private Double salary = 40000.0;

    private Double salaryManager;
    private Double premium; //5% от Выручки

    private Company company; //В какой компании работает

    private double revenue;
    //Продажи менеджера напрямую выручка


    public SalesManager(Company company) {
        this.company = company;

        salaryManager = salary;
        premium = 0.0;

        double anySale = 500000.0;
        addSale(Math.ceil(anySale * Math.random()));
    }


    public void addSale(double sale) //Вообще public нужно бы, но в данном случае лишь private;
    {
        revenue += sale;
        company.addToProfit(sale);
        premium += sale * 0.05;
        salaryManager = salary + premium;
    }

    @Override
    public double getMonthSalary() {
        return salaryManager;
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
