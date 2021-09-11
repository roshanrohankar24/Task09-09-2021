package com.junit_demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.junit_demo.entity.Salary;
import com.junit_demo.repo.SalaryRepo;

@Component
public class SalaryServices {

	@Autowired
	private SalaryRepo salaryRepo;

	public ArrayList<Salary> getSalary() {

	//	ArrayList<Salary> salary=(ArrayList<Salary>) salaryRepo.findAll();
		return (ArrayList<Salary>) salaryRepo.findAll();
		
		

	}

	public Salary getSalary(@PathVariable int Id) {
		Salary salaries=salaryRepo.findById(Id).orElse(new Salary());

		return salaries;

	}

	public Salary addSalary(@RequestBody Salary salary) {
		return salaryRepo.save(salary);

	}

	public void deleteSalary(@PathVariable int Id) {

		salaryRepo.deleteById(Id);

	}
	public Salary upadteSalary(@PathVariable int Id,@RequestBody Salary salary)
	{
		Salary salary2=salaryRepo.findById(Id).orElse(null);
		salary2.setId(salary.getId());
		salary2.setActualSalry(salary.getActualSalry());
		return salaryRepo.save(salary2);
	}

}
