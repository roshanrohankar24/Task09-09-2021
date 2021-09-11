package com.junit_demo.services_test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit_demo.entity.Salary;
import com.junit_demo.repo.SalaryRepo;
import com.junit_demo.services.SalaryServices;

import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;

@RunWith(SpringRunner.class)
@SpringBootTest
class Test_Salary  {

	@MockBean
	SalaryRepo repo2;
	
	@Autowired
	SalaryServices services2;
	
	

	
	@Test
	void testSalary_test_getSalary() throws JSONException {
		
		List<Salary> salary= new ArrayList<Salary>();
		salary.add(new Salary(1,35000));
		salary.add(new Salary(2,45000));
		when(repo2.findAll()).thenReturn(salary);
		assertEquals(2, services2.getSalary().size());
		assertNotNull(salary);
		assertEquals(2, 2);	
		

		
		
	}
	
	

	@Test
	void testSalary_test_saveSalary() {
		Salary salary= new Salary(1,25000);
		//Salary salary2= new Salary(2,25000);
		when(repo2.save(salary)).thenReturn(salary);
		assertEquals(salary, services2.addSalary(salary));
	}
	
	
	@Test
	public void salary_test_getSalaryById() 
	{

		Salary salary= new Salary(1,25000);
	
		when(repo2.findById((Integer) any())).thenReturn(Optional.of(salary));
		assertEquals(1, services2.getSalary(1).getId());
		assertEquals(25000, services2.getSalary(1).getActualSalry());
		assertSame(salary.getId(), services2.getSalary(1).getId());
	}
	
	@Test
	void testSalary_test_deleteSalary() {

		Salary salary= new Salary(1,30000);

//	//	when(repo2.findById(1)).thenReturn(Optional.of(salary));
//		assertEquals(null,services2.deleteSalary(1));
		services2.deleteSalary(1);
	//	repo2.deleteById(1);
		verify(repo2,times(1)).deleteById(1);
	//	assertNull(salary);
		
		
	}
	@Test
	void test_UpdateSalary()
	{
		Salary salary= new Salary(1,30000);
		
		Salary salary1= new Salary();
		salary1.setActualSalry(35000);
		
		
		when(repo2.findById(1)).thenReturn(Optional.of(salary));
		services2.upadteSalary(1, salary1);
		verify(repo2,times(1)).save(salary1);

        assertEquals(35000, salary.getActualSalry());
	}





}
