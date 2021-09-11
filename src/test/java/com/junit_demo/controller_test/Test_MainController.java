package com.junit_demo.controller_test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit_demo.controller.MainController;
import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.entity.Salary;
import com.junit_demo.services.EmployeeServices;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
class Test_MainController {

	@MockBean
	EmployeeServices employeeServices;

	@Autowired
	MockMvc mockMvc;

	Department department = new Department();
	Salary salary = new Salary();
	List<Attendance> attendances = new ArrayList<Attendance>();

	@Test
	void test_GetEmployee() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances));

		when(employeeServices.getEmployee()).thenReturn((ArrayList<Employee>) employees);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Employees")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	void test_GetEmployeeNull() throws Exception {
		List<Employee> employees = new ArrayList<Employee>();

		when(employeeServices.getEmployee()).thenReturn(employees);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Employees")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_GetEmployeeById() throws Exception {
	

		Employee employee=new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);
	     when(employeeServices.isEmployeeExist(1)).thenReturn(true);
         when(employeeServices.getEmployees(1)).thenReturn(employee);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Employees/{EmployeeId}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	
	
	
	@Test
	void test_GetEmployeeByIdNull() throws Exception {
	

		Employee employee=new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);
	     when(employeeServices.isEmployeeExist(2)).thenReturn(false);
       //  when(employeeServices.getEmployees(1)).thenReturn(employee);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Employees/{EmployeeId}",2)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_AddEmployeeBy() throws Exception {
//		List<Employee> employees = new ArrayList<Employee>();
//		employees.add(new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
//				attendances));

		Employee employee=new Employee(1, "Abc", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);
		//when(employeeServices.addEmployees(employee)).thenReturn(employee);
//employeeServices.addEmployees(employee);
//verify(employeeServices,times(1)).addEmployees(employee);
//		String expected=
//		"{\"department\":{\"departmentId\":1,\"
//		        "departmentName": "It"
//		    },
//		    "salary": {
//		        "id": 1,
//		        "actualSalry": 35000
//		    },
//		    "attendance": [
//		        {
//		            "id": 1,
//		            "year": 2021,
//		            "month": 4,
//		            "workingDays": 20
//		        },
//		        {
//		            "id": 2,
//		            "year": 2021,
//		            "month": 5,
//		            "workingDays": 29
//		        }
//		    ],
//		    "employeeeName": "Roshan",
//		    "employeeId": 1,
//		    "dateOfBirth": "1995-04-24",
//		    "dateOfJoining": "2021-08-02"
//		}
//		
//		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.post("/Employees")
//				.accept(MediaType.APPLICATION_JSON).content(asJso)
//				.contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(builder).andExpect(status().isCreated()).andReturn();
//		System.out.println(result.getResponse().getContentAsString());

	}
	
	
	@Test
	void test_DeleteEmployee() throws Exception {
		Employee employee=new Employee(1, "Abc", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);

        when(employeeServices.isEmployeeExist(1)).thenReturn(true);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.delete("/Employees/{EmployeeId}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	@Test
	void test_DeleteEmployeeNull() throws Exception {
		Employee employee=new Employee(1, "Abc", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);
	       when(employeeServices.isEmployeeExist(2)).thenReturn(false);
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.delete("/Employees/{EmployeeId}",2)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
	
	
	@Test
	void test_UpdateEmployee() throws Exception {
		Employee employee=new Employee(1, "Abc", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);
		String expected = "{\"EmployeeName\":\"Roshan\"}";
        when(employeeServices.getEmployees(1)).thenReturn(employee);
	
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.put("/Employees/{EmployeeId}",1)
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(employee.getEmployeeeName());

	}
	@Test
	void test_UpdateEmployeeNull() throws Exception {
		Employee employee=new Employee(1, "Abc", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);

		String expected = "{\"EmployeeName\":\"Roshan\"}";
		
        when(employeeServices.getEmployees(2)).thenReturn(null);

		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.put("/Employees/{EmployeeId}",2)
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}


}
