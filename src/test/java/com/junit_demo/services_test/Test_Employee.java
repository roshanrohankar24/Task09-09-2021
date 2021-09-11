package com.junit_demo.services_test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.entity.Salary;
import com.junit_demo.repo.AttendanceRepo;
import com.junit_demo.repo.EmployeeRepo;
import com.junit_demo.reportModel.AttendanceReport;
import com.junit_demo.services.AttendanceServices;
import com.junit_demo.services.EmployeeServices;


@SpringBootTest
class Test_Employee {

	@MockBean
	EmployeeRepo repo;
	
	@Autowired
	EmployeeServices services;
	
	Department department=new Department();
	Salary salary=new Salary();
	List<Attendance> attendances=new ArrayList<Attendance>();
	
	@Test
	public void test_GetEmployee() {
		
		List<Employee> employees=new ArrayList<Employee>();	
		employees.add(new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20), department,
				salary, attendances));
		employees.add(new Employee());
	
		
		for(Employee e:employees)
		{
			System.out.println(e.getEmployeeeName());
		}
		
		when(repo.findAll()).thenReturn(employees);
		assertEquals(2, services.getEmployee().size());

	}
	@Test
	public void test_SaveEmployee()
	{
		Employee employees=new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20),department,salary,attendances);	
		when(repo.save(employees)).thenReturn(employees);
		assertEquals(employees, services.addEmployees(employees));
		assertEquals(employees.getEmployeeeName(),services.addEmployees(employees).getEmployeeeName());
		
	}
	
	@Test
	public void test_deleteEmployee()
	{
		Employee employees=new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20),department,salary,attendances);	
	
		
		services.deleteEmployees(1);
		verify(repo,times(1)).deleteById(1);;
		
	}
	
	@Test
	void test_GetEmployeeById()
	{
		Employee employees=new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20),
				department,salary,attendances);
		
		when(repo.findById((Integer) any())).thenReturn(Optional.of(employees));
		assertEquals(1, services.getEmployees(1).getEmployeeId());
		assertEquals("Roshan", services.getEmployees(1).getEmployeeeName());
	}
	
	@Test
	void test_UpdateEmployee()
	{
		Employee employees=new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20),
				department,salary,attendances);
		
		when(repo.findById((Integer) any())).thenReturn(Optional.of(employees));
		
		
		Employee newEmployee=new Employee(1,"ABC",new Date(1995-04-24),new Date(2021-10-20),
				department,salary,attendances);
		
		services.upadteEmployee(1, newEmployee);
		verify(repo).save(newEmployee);
		assertEquals("ABC", employees.getEmployeeeName());

		
	}
	
	
	
	@Test
	void test_isExit()
	{
		Employee employees=new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20),
				department,salary,attendances);
	    
	    when(repo.findById(1)).thenReturn(Optional.of(employees));
	    assertEquals(true,services.isEmployeeExist(1));
	   assertNotNull(services.isEmployeeExist(1)); 
	
	}
	@Test
	void test_isExitNull()
	{
		Employee employees=new Employee();
	    
	    when(repo.findById(1)).thenReturn(null);
	    assertEquals(false,services.isEmployeeExist(1));
	 
	
	}
	
	
	
}
