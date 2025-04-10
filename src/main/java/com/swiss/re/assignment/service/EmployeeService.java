package com.swiss.re.assignment.service;

import com.swiss.re.assignment.model.Employee;

import java.util.Map;

public class EmployeeService {
    public void employeeSalaryAnalyze(Map<Long, Employee> employees) {
        Employee ceo = employees.values().stream()
                .filter(e -> e.managerId == 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No CEO found"));

        employees.values().stream()
                .filter(manager -> !manager.subordinates.isEmpty())
                .map(manager -> {
                    double avgSubSalary = manager.subordinates.stream()
                            .mapToDouble(e -> e.salary)
                            .average()
                            .orElse(0.0);
                    double minSalary = avgSubSalary * 1.2;
                    double maxSalary = avgSubSalary * 1.5;
                    return new Object[]{manager, minSalary, maxSalary};
                })
                .forEach(data -> {
                    Employee manager = (Employee) data[0];
                    double minSalary = (double) data[1];
                    double maxSalary = (double) data[2];

                    if (manager.salary < minSalary) {
                        System.out.printf("Manager %s %s (ID: %d) earns LESS than required by %.2f%n",
                                manager.firstname,manager.lastname, manager.id, minSalary - manager.salary);
                    } else if (manager.salary > maxSalary) {
                        System.out.printf("Manager %s %s (ID: %d) earns MORE than allowed by %.2f%n",
                                manager.firstname,manager.lastname, manager.id, manager.salary - maxSalary);
                    }
                });


        employees.values().stream()
                .map(e -> Map.entry(e, getDepth(e, employees)))
                .filter(entry -> entry.getValue() > 4)
                .forEach(entry -> {
                    Employee e = entry.getKey();
                    long depth = entry.getValue();
                    System.out.printf("Employee %s %s (ID: %d) has TOO LONG reporting line: %d levels%n",
                            e.firstname,e.lastname, e.id, depth);
                });
    }

    private long getDepth(Employee e, Map<Long, Employee> map) {
        int depth = 0;
        while (e.managerId != 0) {
            e = map.get(e.managerId);
            depth++;
        }
        return depth;
    }
}
