package main.java.app;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
  private List<Employee> employees;

  public List<Employee> generateEmployees() {
    employees = new ArrayList<Employee>();

    employees.add(new Employee("1234", "Daniel", "SS", "Lecturer", "dan@ss.org", 6000));
    employees.add(new Employee("3524", "Sam", "DA", "Cook", "sam@ss.org", 5000));
    employees.add(new Employee("4332", "Ben", "SS", "Cleaner", "ben@ss.org", 4000));
    employees.add(new Employee("3292", "Raj", "SS", "Professor", "raj@ss.org", 12000));
    employees.add(new Employee("2332", "Lim", "DA", "Admin", "lim@ss.org", 5000));

    return employees;
  }
}
