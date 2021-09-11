package com.junit_demo.services_test;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.repo.DeprtmentRepo;

import com.junit_demo.services.DepartmentServices;

@SpringBootTest
class Test_Department {
	
	@Autowired
	DepartmentServices services;
	
	@MockBean
	 DeprtmentRepo repo;
	


	@Test
	void test_GetDepartment() {
		List<Employee> employees=new ArrayList<Employee>();	
		
		List<Department> department= new ArrayList<Department>();
		department.add(new Department(1,"It",employees));
		department.add(new Department(2,"F",employees));
		when(repo.findAll()).thenReturn(department);
		
		assertEquals(2, services.getDepartment().size());
	
		assertEquals(department.size(), services.getDepartment().size());

		assertNotNull(services.getDepartment().size());

		
	}
	
	@Test
	public void test_SaveDepartment()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department dep=new Department(1,"It",employees);
	
		when(repo.save(dep)).thenReturn(dep);
		assertEquals(dep, services.addDepartment(dep));
		assertEquals(dep.getDepartmentName(),services.addDepartment(dep).getDepartmentName());
		
	}
	
	@Test
	public void test_getDepartmentById()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department department=new Department(1,"It",employees);
		when(repo.findById((Integer) any())).thenReturn(Optional.of(department));			
		assertEquals(department.getDepartmentName(),services.getDepartment(1).get().getDepartmentName());
		
	}
	
	
	@Test
	public void test_deleteDepartment()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department dep=new Department(1,"It",employees);
		
		services.deleteDepartment(1);
		verify(repo,times(1)).deleteById(1);
		
	}

	@Test
	void test_dtoDepartment()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department dep=new Department(1,"It",employees);
		
		when(repo.save(dep)).thenReturn(dep);
		assertEquals(dep.getDepartmentId(), services.dto(dep).getDepartmentId());
		
	}
	
	@Test
	void test_dtoDepartmentList()
	{
	List<Employee> employees=new ArrayList<Employee>();	
		
		List<Department> department= new ArrayList<Department>();
		department.add(new Department(1,"It",employees));
		department.add(new Department(2,"F",employees));
		when(repo.saveAll(department)).thenReturn(department);
		assertEquals(2, services.dto(department).size());
		
	}
	
	@Test
	void test_getOnlyDepertment()
	{
	List<Employee> employees=new ArrayList<Employee>();	
		
		List<Department> department= new ArrayList<Department>();
		department.add(new Department(1,"It",employees));
		department.add(new Department(2,"F",employees));
		when(repo.findAll()).thenReturn(department);
		assertEquals(2, services.getOnlyDepartment().size());
		
	}
	
	
	@Test
	void test_getOnlyDepertmentById()
	{
	
	List<Employee> employees=new ArrayList<Employee>();	
	Department department=new Department(1,"It",employees);
		when(repo.findById((Integer) any())).thenReturn(Optional.of(department));
		assertEquals("It", services.getOnlyDepartment(1).getDepartmentName());
		
	}
	
	

	@Test
	void test_updateOnlyDepertment()
	{
	
	List<Employee> employees=new ArrayList<Employee>();	
	Department department=new Department(1,"It",employees);
	when(repo.findById((Integer) any())).thenReturn(Optional.of(department));
	
	Department newDepartment=new Department(1,"F",employees);
	services.updateOnlyDepartment(1, newDepartment);
	verify(repo).save(newDepartment);
	assertEquals("F", department.getDepartmentName());
	assertEquals("F", services.getOnlyDepartment(1).getDepartmentName());
		
	}
	
	@Test
	void test_isExit()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department department=new Department(1,"It",employees);
		
	    when(repo.findById(1)).thenReturn(Optional.of(department));
	    assertEquals(true,services.isDepartmentExist(1));
	   assertNotNull(services.isDepartmentExist(1)); 
	
	}
	@Test
	void test_isExitNull()
	{
		List<Employee> employees=new ArrayList<Employee>();	
		Department department=new Department(1,"It",employees);
	    
	    when(repo.findById(1)).thenReturn(null);
	    assertEquals(false,services.isDepartmentExist(1));
	 
	
	}
	
	

}
