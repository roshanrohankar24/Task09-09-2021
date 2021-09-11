package com.junit_demo.controller_test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit_demo.controller.DepartmentController;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.repo.DeprtmentRepo;
import com.junit_demo.services.DepartmentServices;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DepartmentController.class)
class Test_DepartmentController {

	@MockBean
	DepartmentServices departmentServices;

	@Autowired
	MockMvc mockMvc;
	

	@Test
	void test_GetDepartment() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();

		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		when(departmentServices.getDepartment()).thenReturn((ArrayList<Department>) department);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	void test_GetDepartmentNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();

		List<Department> department = new ArrayList<Department>();

		when(departmentServices.getDepartment()).thenReturn((ArrayList<Department>) department);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	void test_GetDepartmentById() throws Exception {


	List<Employee> employees=new ArrayList<Employee>();	
	Department department=new Department(1,"It",employees);
	when(departmentServices.isDepartmentExist(1)).thenReturn(true);
	when(departmentServices.getDepartment(1)).thenReturn(Optional.of(department));
	RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/{Id}", 1)
	.contentType(MediaType.APPLICATION_JSON);

MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
System.out.println(result.getResponse().getContentAsString());
	
	
	}

	@Test
	void test_GetDepartmentByIdNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();

		Department department = new Department(1, "It", employees);
		when(departmentServices.isDepartmentExist(2)).thenReturn(false);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/2")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	void test_DeleteDepartment() throws Exception {
		
	List<Employee> employees=new ArrayList<Employee>();	
	
		Department department= new Department(1,"It",employees);
		when(departmentServices.isDepartmentExist(1)).thenReturn(true);
		
		 RequestBuilder builder=  (RequestBuilder) MockMvcRequestBuilders.delete("/Department/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON);
		 MvcResult result=mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		 System.out.println(result.getResponse().getContentAsString());
		
	}
	@Test
	void test_DeleteDepartmentNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();

		Department department = new Department(1, "It", employees);
		when(departmentServices.isDepartmentExist(2)).thenReturn(false);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.delete("/Department/{Id}",2)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_getOnlyDepartment() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		
		when(departmentServices.getOnlyDepartment()).thenReturn((ArrayList<Department>) department);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/Department")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_getOnlyDepartmentNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		
		
		when(departmentServices.getOnlyDepartment()).thenReturn((ArrayList<Department>) department);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/Department")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_getOnlyDepartmentById() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		
		when(departmentServices.isDepartmentExist(1)).thenReturn(true);
		when(departmentServices.getOnlyDepartment(1)).thenReturn(department.get(0));
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/Department/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_getOnlyDepartmentByIdNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		
		when(departmentServices.isDepartmentExist(3)).thenReturn(false);
		when(departmentServices.getOnlyDepartment(3)).thenReturn(null);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Department/Department/{Id}",3)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	
	@Test
	void test_UpdateDepartment() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		String expected = "{\"DepartmentId\":\"2\",\"DepartmentName\":\"Finance\"}";
		

		
		when(departmentServices.isDepartmentExist(2)).thenReturn(true);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.put("/Department/Department/{Id}",2)
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(department.get(1).getDepartmentName());

	}

	@Test
	void test_UpdateDepartmentNull() throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		List<Department> department = new ArrayList<Department>();
		department.add(new Department(1, "It", employees));
		department.add(new Department(2, "F", employees));
		String expected = "{\"DepartmentId\":\"2\",\"DepartmentName\":\"Finance\"}";
		
		
		when(departmentServices.isDepartmentExist(3)).thenReturn(false);
		
	
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.put("/Department/Department/{Id}",3)
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isBadRequest()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	

	}
	
	

}
