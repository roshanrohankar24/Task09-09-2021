package com.junit_demo.controller_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.junit_demo.controller.ReportController;
import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.entity.Salary;
import com.junit_demo.repo.AttendanceRepo;
import com.junit_demo.repo.EmployeeRepo;
import com.junit_demo.reportModel.AttendanceReport;
import com.junit_demo.reportModel.EmployeeReport;
import com.junit_demo.reportModel.EmployeeReport1;
import com.junit_demo.reportModel.ReportConvertor;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ReportController.class)
class Test_ReportController {

	@MockBean
	ReportConvertor convertor;
	
	@Autowired
	MockMvc mockMvc;

	
@MockBean
EmployeeRepo repo;
@MockBean
AttendanceRepo attendanceRepo ;

	
	Department department=new Department();
	Salary salary=new Salary();
	List<Attendance> attendances=new ArrayList<Attendance>();
	
	@Test
	void test_GetReport() throws Exception
	{
		List<Employee> employees=new ArrayList<Employee>();	
		employees.add(new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20), department,
				salary, attendances));
		List<EmployeeReport> reports=new ArrayList<EmployeeReport>();
		reports.add(new EmployeeReport("Roshan",new Date(1995-04-24),new Date(2021-10-20),null,1));
		
	    when(repo.findAll()).thenReturn(employees);
		when(convertor.dto(employees)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	void test_GetReportNull() throws Exception
	{
		List<Employee> employees=new ArrayList<Employee>();	
	
	    when(repo.findAll()).thenReturn(employees);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	void test_GetReportById() throws Exception
	{
		List<Employee> employees=new ArrayList<Employee>();	
		employees.add(new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20), department,
				salary, attendances));
		List<EmployeeReport> reports=new ArrayList<EmployeeReport>();
		reports.add(new EmployeeReport("Roshan",new Date(1995-04-24),new Date(2021-10-20),null,1));
		
	    when(repo.findAll()).thenReturn(employees);
	    when(convertor.reportById(employees, 1)).thenReturn(reports);
	//	when(convertor.dto(employees)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	void test_GetReportByIdNull() throws Exception
	{
		List<Employee> employees=new ArrayList<Employee>();	
		employees.add(new Employee(1,"Roshan",new Date(1995-04-24),new Date(2021-10-20), department,
				salary, attendances));
		List<EmployeeReport> reports=new ArrayList<EmployeeReport>();
		
	    when(repo.findAll()).thenReturn(employees);
	    when(convertor.reportById(employees, 2)).thenReturn(reports);
	//	when(convertor.dto(employees)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report/{Id}",2)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	
	@Test
	void test_GetReport1() throws Exception
	{
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employees));
		attendances.add(new Attendance(2,5,2021,24,employees));
		
		
		List<EmployeeReport1> reports=new ArrayList<EmployeeReport1>();
		reports.add(new EmployeeReport1("","",4,2021,0,0.0,""));
		
	    when(attendanceRepo.findAll()).thenReturn(attendances);
		when(convertor.dto1(attendances)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report1")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	void test_GetReport1Null() throws Exception
	{
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();

		
		List<EmployeeReport1> reports=new ArrayList<EmployeeReport1>();

	    when(attendanceRepo.findAll()).thenReturn(attendances);
		when(convertor.dto1(attendances)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report1")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	void test_GetReport1ById() throws Exception
	{
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employees));
		attendances.add(new Attendance(2,5,2021,24,employees));
		
		
		List<EmployeeReport1> reports=new ArrayList<EmployeeReport1>();
		reports.add(new EmployeeReport1("","",4,2021,0,0.0,""));
		
	    when(attendanceRepo.findAll()).thenReturn(attendances);
		when(convertor.report1ById(attendances, 1)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report1/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	void test_GetReport1ByIdNull() throws Exception
	{
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employees));
		attendances.add(new Attendance(2,5,2021,24,employees));
		
		
		List<EmployeeReport1> reports=new ArrayList<EmployeeReport1>();
	
		
	    when(attendanceRepo.findAll()).thenReturn(attendances);
		when(convertor.report1ById(attendances, 2)).thenReturn(reports);
		
		RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Report1/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
}
