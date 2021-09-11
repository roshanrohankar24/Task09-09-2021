package com.junit_demo.controller_test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.junit_demo.controller.AttendanceController;
import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.entity.Salary;
import com.junit_demo.reportModel.AttendanceReport;
import com.junit_demo.reportModel.ReportConvertor;
import com.junit_demo.services.AttendanceServices;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AttendanceController.class)
class Test_AttendanceController {

	@MockBean
	private AttendanceServices attendanceServices;

	@Autowired
	MockMvc mockMvc;
	
@MockBean
ReportConvertor convertor;

Department department = new Department();
Salary salary = new Salary();
List<Attendance> attendances = new ArrayList<Attendance>();
	
	@Test
	void test_GetAttendance() throws Exception {
		
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employees));
		attendances.add(new Attendance(2,5,2021,24,employees));

		when(attendanceServices.getAttendance()).thenReturn((ArrayList<Attendance>) attendances);

		mockMvc.perform(get("/Attendance")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
	}

	@Test
	void test_GetAttendanceNull() throws Exception {
		
		Employee employees=new Employee();		
		List<Attendance> attendances= new ArrayList<Attendance>();
		when(attendanceServices.getAttendance()).thenReturn((ArrayList<Attendance>) attendances);

		mockMvc.perform(get("/Attendance")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void test_GetAttendanceById() throws Exception {
		
		Employee employee=new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);	
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employee));
		attendances.add(new Attendance(2,5,2021,24,employee));
		List<AttendanceReport> report=new ArrayList<AttendanceReport>();
		report.add(new AttendanceReport(1,1,"Roshan",4,2021,24));
        when(attendanceServices.isAttendanceExist(1)).thenReturn(true);
        when(attendanceServices.getAttendance(1)).thenReturn(report);

        RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Attendance/{EmployeeId}",1)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	@Test
	void test_GetAttendanceByIdNull() throws Exception {
		Employee employee=new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department, salary,
				attendances);	
		List<Attendance> attendances= new ArrayList<Attendance>();
		attendances.add(new Attendance(1,4,2021,24,employee));
		attendances.add(new Attendance(2,5,2021,24,employee));
		List<AttendanceReport> report=new ArrayList<AttendanceReport>();
		report.add(new AttendanceReport(1,1,"Roshan",4,2021,24));
        when(attendanceServices.isAttendanceExist(3)).thenReturn(false);
        when(attendanceServices.getAttendance(3)).thenReturn(report);

        RequestBuilder builder = (RequestBuilder) MockMvcRequestBuilders.get("/Attendance/{EmployeeId}",3)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isNotFound()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	
	
	@Test
	void test_DeleteAttendanceById() throws Exception {
		
		Employee employees=new Employee();	
	    Attendance attendances= new Attendance(1,4,2021,24,employees);
	    when(attendanceServices.isAttendanceExist(1)).thenReturn(true);
     
		mockMvc.perform(delete("/Attendance/{Id}",1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		System.out.println(attendances.getId());
	}
	@Test
	void test_DeleteAttendanceByIdNull() throws Exception {
		
		Employee employees=new Employee();	
	    Attendance attendances= new Attendance(1,4,2021,24,employees);
	    when(attendanceServices.isAttendanceExist(2)).thenReturn(false);
	
			mockMvc.perform(delete("/Attendance/{Id}",2)
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isNotFound());
		
	}

	
	
}
