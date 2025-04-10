package com.swiss.re.assignment;

import com.swiss.re.assignment.model.Employee;
import com.swiss.re.assignment.service.EmployeeService;
import com.swiss.re.assignment.util.CsvReader;

import java.util.Map;


public class AssignmentApplication {

	public static void main(String[] args) throws Exception {
		Map<Long, Employee> employees = CsvReader.readEmployees("employees.csv");
		new EmployeeService().employeeSalaryAnalyze(employees);
	}

}
