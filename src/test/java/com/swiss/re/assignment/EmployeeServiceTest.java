package com.swiss.re.assignment;
import com.swiss.re.assignment.model.Employee;
import com.swiss.re.assignment.service.EmployeeService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
public class EmployeeServiceTest {
    @Test
    public void testSalaryWithinBounds() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "john", 200000, 0));
        emps.put(2L, new Employee(2, "Mgr1", "mack",90000, 1));
        emps.put(3L, new Employee(3, "Emp1", "Mohan",60000, 2));
        emps.put(4L, new Employee(4, "Emp2", "show",60000, 2));

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    @Test
    public void testSalaryTooLow() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "Mike",200000, 0));
        emps.put(2L, new Employee(2, "Mgr1", "Ronan",70000, 1)); // too low
        emps.put(3L, new Employee(3, "Emp1", "Kevin",60000, 2));
        emps.put(4L, new Employee(4, "Emp2", "Rodes",60000, 2));

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    @Test
    public void testSalaryTooHigh() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "micky",200000, 0));
        emps.put(2L, new Employee(2, "Mgr1", "donald",120000, 1)); // too high
        emps.put(3L, new Employee(3, "Emp1", "shaun",50000, 2));
        emps.put(4L, new Employee(4, "Emp2", "ronan",50000, 2));

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    @Test
    public void testLongReportingLine() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "micky",200000, 0));
        for (long i = 2; i <= 7; i++) {
            emps.put(i, new Employee(i, "Emp" + i, "donald"+ i,50000, i - 1));
        }

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    @Test
    public void testManagerWithNoSubordinates() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "micky",200000, 0));
        emps.put(2L, new Employee(2, "Mgr1", "donald",100000, 1)); // no subs

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    @Test
    public void testMultipleManagers() {
        Map<Long, Employee> emps = new HashMap<>();
        emps.put(1L, new Employee(1, "CEO", "micky",200000, 0));
        emps.put(2L, new Employee(2, "Mgr1", "donald",105000, 1));
        emps.put(3L, new Employee(3, "Mgr2", "Orland",95000, 1));
        emps.put(4L, new Employee(4, "Emp1", "oven",60000, 2));
        emps.put(5L, new Employee(5, "Emp2", "mars",60000, 3));

        buildHierarchy(emps);
        new EmployeeService().employeeSalaryAnalyze(emps);
    }

    private void buildHierarchy(Map<Long, Employee> emps) {
        for (Employee emp : emps.values()) {
            if (emp.managerId != 0 && emps.containsKey(emp.managerId)) {
                emps.get(emp.managerId).subordinates.add(emp);
            }
        }
    }
}
