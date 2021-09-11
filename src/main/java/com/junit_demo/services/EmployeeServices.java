package com.junit_demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.junit_demo.entity.Employee;
import com.junit_demo.repo.EmployeeRepo;

@Component
public class EmployeeServices {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	public List<Employee> getEmployee()
	{
		return employeeRepo.findAll();
		
	}
	public Employee getEmployees(@PathVariable int EmployeeId) {

		
		return employeeRepo.findById(EmployeeId).orElseThrow();
	}


	public Employee addEmployees(@RequestBody Employee employee) {
		
	
			
			return employeeRepo.save(employee);
		
	}

	public void deleteEmployees(@PathVariable int EmployeeId) {
		employeeRepo.deleteById(EmployeeId);
	}


	public Employee upadteEmployee(@PathVariable int EmployeeId, @RequestBody Employee employeedeatils) {

		Employee employee = employeeRepo.findById(EmployeeId).orElse(null);
		employee.setEmployeeeName(employeedeatils.getEmployeeeName());
		employee.setDateOfBirth(employeedeatils.getDateOfBirth());
		employee.setDateOfJoining(employeedeatils.getDateOfJoining());
		return employeeRepo.save(employee);

	}
	public boolean isEmployeeExist(int employeeId) {
	
		if(employeeRepo.findById(employeeId)==null)
		{
			return false;
		}
		else {
		return true;
		}
	}
}
