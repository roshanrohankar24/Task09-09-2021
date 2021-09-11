package com.junit_demo.controller_test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.junit_demo.controller.SalaryController;
import com.junit_demo.entity.Salary;
import com.junit_demo.services.SalaryServices;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers =  SalaryController.class)
class Test_SalaryController {

	
	@MockBean
	private SalaryServices salaryServices;

	@Autowired
	MockMvc mockMvc;
	
	@Test
	void test_GetSalary() throws Exception {
		
		List<Salary> salary= new ArrayList<Salary>();
		salary.add(new Salary(1,35000));

		
		when(salaryServices.getSalary()).thenReturn((ArrayList<Salary>) salary);
		
		
//		Mockito.when(salaryServices.getSalary()).thenReturn((ArrayList<Salary>) salary);
//
//		
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.
//				get("/Salary").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result.getResponse().getContentAsString());
//		String expected = "{id:1,ActualSalary:35000}";
//		JSONAssert.assertEquals(expected, result.getResponse()
//				.getContentAsString(), false);
		
		
		mockMvc.perform(get("/Salary")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	void test_GetSalaryNull() throws Exception {
		
		List<Salary> salary= new ArrayList<Salary>();
	//	salary.add(new Salary(1,35000));
		when(salaryServices.getSalary()).thenReturn((ArrayList<Salary>) salary);

		mockMvc.perform(get("/Salary")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void test_GetSalaryById() throws Exception {
		
		Salary salary= new Salary(1,25000);
		when(salaryServices.getSalary(1)).thenReturn(salary);
		
		mockMvc.perform(get("/Salary/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void test_GetSalaryByIdNull() throws Exception {
		
		Salary salary= new Salary(1,25000);
		when(salaryServices.getSalary(2)).thenReturn(null);
		
		mockMvc.perform(get("/Salary/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	
	
	@Test
	void test_DeleteSalaryById() throws Exception {
		
		Salary salary= new Salary(1,25000);
		
		when(salaryServices.getSalary(1)).thenReturn(salary);
		mockMvc.perform(delete("/Salary/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void test_DeleteSalaryByIdNull() throws Exception {
		
		Salary salary= new Salary(1,25000);
		when(salaryServices.getSalary(2)).thenReturn(null);
		
		mockMvc.perform(delete("/Salary/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void test_UpdateSalaryById() throws Exception {
		
		Salary salary= new Salary(1,25000);
		String expected = "{\"Id\":\"1\",\"ActualSalary\":\"35000\"}";
		when(salaryServices.getSalary(1)).thenReturn(salary);
		mockMvc.perform(put("/Salary/1")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	void test_UpdateSalaryByIdNull() throws Exception {
		
		Salary salary= new Salary(1,25000);
		String expected = "{\"Id\":\"1\",\"ActualSalary\":\"35000\"}";
		when(salaryServices.getSalary(2)).thenReturn(null);
		
		mockMvc.perform(put("/Salary/2")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
