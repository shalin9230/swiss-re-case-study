package com.swiss.re.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public long id;
    public String firstname;
    public String lastname;
    public double salary;
    public long managerId;

    public List<Employee> subordinates = new ArrayList<>();

    public Employee(long id, String firstname,String lastname, double salary, long managerId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.managerId = managerId;
    }
}
