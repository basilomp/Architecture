package composite;

import java.util.ArrayList;

public class Developer implements Employee{

    protected float salary;
    protected String name;
    protected ArrayList<String> roles;

    public Developer(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return this.salary;
    }

    @Override
    public ArrayList<String> getRoles() {
        return this.roles;
    }
}
