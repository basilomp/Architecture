package composite;

public class EmployeeApp {
    public static void main(String[] args) {
        Employee john = new Developer("John Doe", 12000f);
        Employee jane = new Developer("Jane Doe", 15000f);

        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);

        System.out.printf("Net salaries: %f\n", organization.getNetSalaries());
    }
}
