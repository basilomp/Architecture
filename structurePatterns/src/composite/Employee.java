package composite;

import java.util.ArrayList;

public interface Employee {

    String getName();
    void setSalary(float salary);
    float getSalary();
    ArrayList<String> getRoles();
}
