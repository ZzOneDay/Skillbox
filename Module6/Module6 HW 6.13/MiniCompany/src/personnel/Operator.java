package personnel;

import comp.Company;

public class Operator implements Employee {
    private String position = "Operator";
    private Double salaryOperator = 45000.0;

    private Company company; //В какой компании работает, тут не нужно, но пусть будет.

    //Предположем благодаря его работе, выручка в компанию
    private double revenue = 25000;

    public Operator(Company company) {
        this.company = company;
        company.addToProfit(revenue);
    }


    @Override
    public double getMonthSalary() {
        return salaryOperator;
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
