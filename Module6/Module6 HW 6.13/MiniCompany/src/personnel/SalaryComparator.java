package personnel;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {

    private boolean TopToLow;

    public SalaryComparator(boolean TopToLow) {
        this.TopToLow = TopToLow;
    }


    @Override
    public int compare(Employee o1, Employee o2) {
        int index = 0;

        if (o1.getMonthSalary() > o2.getMonthSalary()) {
            index = 1;
        }
        if (o1.getMonthSalary() < o2.getMonthSalary()) {
            index = -1;
        }
        if (TopToLow) {
            index = index * (-1);
        }
        return index;
    }
}
