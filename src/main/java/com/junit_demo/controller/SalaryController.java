package com.junit_demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.junit_demo.entity.Salary;
import com.junit_demo.services.SalaryServices;

@RestController
public class SalaryController {

	@Autowired
	private SalaryServices salaryServices;

	@GetMapping("/Salary")
	public ResponseEntity<Object> getSalary() {
		ArrayList<Salary> salaries = salaryServices.getSalary();
		if (salaries.size() != 0) {
			return new ResponseEntity<Object>(salaries, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Salary List Is Empty", HttpStatus.NOT_FOUND);
		}
		// return (ArrayList<Salary>) salaryServices.getSalary();
	}

	@GetMapping("/Salary/{Id}")
	public ResponseEntity<Object> getSalary(@PathVariable int Id) {

		Salary salary = salaryServices.getSalary(Id);

		if (salary!=null) {
			return new ResponseEntity<Object>(salary, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Salary is not Found", HttpStatus.NOT_FOUND);
		}
		// return salaryServices.getSalary(Id);

	}

//	@PostMapping("/Salary")
//	public Salary addSalary(@RequestBody Salary salary)
//	{
//		return salaryServices.addSalary(salary);
//		
//	}
	@DeleteMapping("/Salary/{Id}")
	public ResponseEntity<Object> deleteSalary(@PathVariable int Id) {

		Salary salary = salaryServices.getSalary(Id);
		if (salary != null) {
			salaryServices.deleteSalary(Id);
			return new ResponseEntity<Object>("Salary Is Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Salary is not Found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/Salary/{Id}")
	public ResponseEntity<Object> updateAttendance(@PathVariable int Id, @RequestBody Salary salary) {
	   Salary salary1 = salaryServices.getSalary(Id);
		if (salary1 != null) {
			salaryServices.upadteSalary(Id, salary);
			return new ResponseEntity<Object>("Salary Updated Succesfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Salary is not Found", HttpStatus.NOT_FOUND);
		}
	}
}
