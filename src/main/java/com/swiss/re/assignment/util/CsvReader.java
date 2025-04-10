package com.swiss.re.assignment.util;

import com.swiss.re.assignment.model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CsvReader {
    public static Map<Long, Employee> readEmployees(String filename) throws Exception {
        Map<Long, Employee> employees = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                long id = Long.parseLong(parts[0]);
                String firstname = parts[1];
                String lastname = parts[2];
                double salary = Double.parseDouble(parts[3]);
                long managerId = parts.length > 4
                        && !parts[4].isEmpty() ? Integer.parseInt(parts[4]) : 0;
                employees.put(id, new Employee(id, firstname, lastname, salary, managerId));
            }
        }
        employees.values().forEach(employee -> {
            if (employee.managerId != 0) {
                employees.get(employee.managerId).subordinates.add(employee);
            }
        });
        return employees;
    }
}
