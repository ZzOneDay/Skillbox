package comp;

import personnel.*;

import java.util.ArrayList;

public class Company {

    private double profit;
    private double revenueCompany;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Company() {
        profit = 0.0;
    }

    public void addToProfit(Double profitBySalesManager) {
        profit += profitBySalesManager;
    }

    public Double getProfit() {
        return profit;
    }

    public int numberOfEmployees() {
        return employees.size();
    }

    public void hireEmployee(Employee employee) {
        employees.add(employee);
        revenueCompany += employee.revenue();
    }

    public void dismissEmployee(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employee.equals(employees.get(i))) {
                System.out.println("Уволен сотрудник: " + employees.get(i).getJobTitle());
                System.out.println("Выручка компании уменьшилась на : " + employees.get(i).revenue());
                employees.remove(i);
                revenueCompany -= employee.revenue();
                profit -= employee.revenue();
                break;
            }
        }
    }


    public double getRevenueCompany() {
        return revenueCompany;
    }


    private void printInfoAboutEmployee(int idNumber) {
        System.out.println("Должность:" + employees.get(idNumber).getJobTitle()
                + "|" + Math.round(employees.get(idNumber).getMonthSalary()) + " рублей");
    }

    public void getTopSalaryStaff(int count) {
        employees.sort(new SalaryComparator(true));
        printListEmployee(count);
    }

    public void getLowestSalaryStaff(int count) {
        employees.sort(new SalaryComparator(false));
        printListEmployee(count);
    }

    private void printListEmployee(int count) {
        for (int i = 0; i < employees.size(); i++) {
            if (count == employees.size() || i == count) {
                break;
            }
            printInfoAboutEmployee(i);
        }
    }
}
