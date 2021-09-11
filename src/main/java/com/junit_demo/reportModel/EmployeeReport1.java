package com.junit_demo.reportModel;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class EmployeeReport1 {
    private String EmployeeName;
	private String DepartmentName;
	private int Month;
	private int Year;
	private int ActualSalary;
	private double CalculatedSalary;
	private String EmployeeId;
	
	
	public EmployeeReport1(String employeeName, String departmentName, int month, int year, int actualSalary,
			double calculatedSalary, String employeeId) {
		super();
		EmployeeName = employeeName;
		DepartmentName = departmentName;
		Month = month;
		Year = year;
		ActualSalary = actualSalary;
		CalculatedSalary = calculatedSalary;
		EmployeeId = employeeId;
	}
	
	
	
}
