package composite;

import java.util.ArrayList;

public class Designer implements Employee{
    protected float salary;
    protected String name;
    protected ArrayList<String> roles;

    public Designer(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return salary;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public ArrayList<String> getRoles() {
        return roles;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }
}
