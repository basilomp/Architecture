package composite;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    protected List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public float getNetSalaries() {
        float netSalary = 0;
        for (Employee employee : employees) {
            netSalary += employee.getSalary();
        }
        return netSalary;
    }
}
