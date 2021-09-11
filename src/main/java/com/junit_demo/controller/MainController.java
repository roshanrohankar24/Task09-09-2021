package com.junit_demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.junit_demo.services.EmployeeServices;
import com.junit_demo.entity.Employee;
import com.junit_demo.repo.EmployeeRepo;

@RestController
public class MainController {
	
	@Autowired
	private EmployeeServices employeeServices;

	@GetMapping("/Employees")
	public ResponseEntity<Object> getEmployees() {
		List<Employee> employees= employeeServices.getEmployee();
		if(employees.size()==0)
		{
			return new ResponseEntity<Object>("Employee List Is Empty",HttpStatus.NOT_FOUND);
		}
		else {	
		return new ResponseEntity<>(employees,HttpStatus.OK);

		}
	}
	@GetMapping("/Employees/{EmployeeId}")
	public ResponseEntity<Object> getEmployees(@PathVariable int EmployeeId) {
		

		boolean isEmployeeExist=employeeServices.isEmployeeExist(EmployeeId);
		if(isEmployeeExist)
		{
			Employee employee =employeeServices.getEmployees(EmployeeId);
			return new ResponseEntity<Object>(employee,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>("Employee Not Found",HttpStatus.NOT_FOUND);
		//	throw new NoSuchElementException();
		}
	}

	@PostMapping("/Employees")
	public ResponseEntity<Object> addEmployees(@RequestBody Employee employee) {
//		try {
//			if (employee.getEmployeeeName().isEmpty()) {
//				throw new NoSuchElementException();
//			}
//				employee = employeeServices.addEmployees(employee);
//				return new ResponseEntity<Object>("Employee Created Successfully" + employee.getEmployeeId(),
//						HttpStatus.CREATED);
//			
//		} catch (NoSuchElementException e) {
//			
//			throw new NoSuchElementException();
//		}
	     employee= employeeServices.addEmployees(employee);
		return new ResponseEntity<Object>("Employee Created Successfully"+employee.getEmployeeId(),HttpStatus.CREATED);
	     

	}

	@DeleteMapping("/Employees/{EmployeeId}")
	public ResponseEntity<Object> deleteEmployees(@PathVariable int EmployeeId) {
		
//		try
//		{
//			employeeServices.deleteEmployees(EmployeeId);;
//			return new ResponseEntity<Object>("Employee Deleted Successfully",HttpStatus.OK);	
//		}
//		catch (EmptyResultDataAccessException e) {
//			throw new EmptyResultDataAccessException("Employee Not Found",EmployeeId);
//		
//		}
	
		
		boolean isEmployeeExist=employeeServices.isEmployeeExist(EmployeeId);
		if(isEmployeeExist)
		{
			employeeServices.deleteEmployees(EmployeeId);;
			return new ResponseEntity<Object>("Employee Deleted Successfully",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Object>("Employee Not Found",HttpStatus.NOT_FOUND);
		//	throw new EmptyResultDataAccessException(EmployeeId);
		}
	}

	@PutMapping("Employees/{EmployeeId}")
	public ResponseEntity<Object> upadteEmployee(@PathVariable int EmployeeId, @RequestBody Employee employeedeatils) {

	       employeedeatils=employeeServices.getEmployees(EmployeeId);
	       
	       if(employeedeatils!=null)
	       {
	    	 employeeServices.upadteEmployee(EmployeeId, employeedeatils); 
	    	 return new ResponseEntity<Object>("Employee Udated Successfully",HttpStatus.OK);
	  	   
	       }
	       else
	       {
	    	   return new ResponseEntity<Object>("Employee Not Found",HttpStatus.NOT_FOUND);
	    	 //  throw new NoSuchElementException();
	       }

	}

}
