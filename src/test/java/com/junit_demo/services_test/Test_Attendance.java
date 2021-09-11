package com.junit_demo.services_test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Department;
import com.junit_demo.entity.Employee;
import com.junit_demo.entity.Salary;
import com.junit_demo.repo.AttendanceRepo;
import com.junit_demo.reportModel.AttendanceReport;
import com.junit_demo.reportModel.ReportConvertor;
import com.junit_demo.services.AttendanceServices;

@SpringBootTest
class Test_Attendance {

	@MockBean
	AttendanceRepo repo;

	@Autowired
	AttendanceServices services;

	@MockBean
	private ReportConvertor convertor;

	Department department = new Department();
	Salary salary = new Salary();
	List<Attendance> attendances = new ArrayList<Attendance>();

	@Test
	public void test_GetAttendance() {

		Employee employees = new Employee();
		List<Attendance> attendances = new ArrayList<Attendance>();
		attendances.add(new Attendance(1, 4, 2021, 24, employees));
		attendances.add(new Attendance(2, 5, 2021, 24, employees));
		when(repo.findAll()).thenReturn(attendances);
		assertEquals(2, services.getAttendance().size());

	}

	@Test
	public void test_SaveAttendance() {
		Employee employees = new Employee();
		Attendance attendances = new Attendance(1, 4, 2021, 24, employees);
		when(repo.save(attendances)).thenReturn(attendances);
		assertEquals(attendances, services.addAttendance(attendances));
		assertEquals(attendances.getYear(), services.addAttendance(attendances).getYear());

	}

	@Test
	public void test_deleteAttendance() {
		Employee employees = new Employee();
		Attendance attendances = new Attendance(1, 4, 2021, 24, employees);

		repo.delete(attendances);
		verify(repo, times(1)).delete(attendances);

	}

	@Test
	void test_GetAttendanceById() {
		Employee employees = new Employee(1, "Roshan", new Date(1995 - 04 - 24), new Date(2021 - 10 - 20), department,
				salary, attendances);
		List<Attendance> attendances = new ArrayList<Attendance>();
		attendances.add(new Attendance(1, 4, 2021, 25, employees));
		List<AttendanceReport> report = new ArrayList<AttendanceReport>();
		report.add(new AttendanceReport(1, 1, null, 4, 2021, 24));

		when(repo.findAll()).thenReturn(attendances);
		when(convertor.attendanceReport(attendances, 1)).thenReturn(report);

		assertEquals(report, services.getAttendance(1));
	}

	@Test
	void test_DeleteAttendance() {

		Employee employees = new Employee();
		Attendance attendances = new Attendance(1, 4, 2021, 24, employees);

		services.deleteAttendance(1);
		verify(repo, times(1)).deleteById(1);

	}

	@Test
	void test_isExit() {
		Employee employees = new Employee();
		Attendance attendances = new Attendance(1, 4, 2021, 24, employees);

		when(repo.findById(1)).thenReturn(Optional.of(attendances));
		assertEquals(true, services.isAttendanceExist(1));
		assertNotNull(services.isAttendanceExist(1));

	}

	@Test
	void test_isExitNull() {
		Employee employees = new Employee();
		Attendance attendances = new Attendance();

		when(repo.findById(1)).thenReturn(null);
		assertEquals(false, services.isAttendanceExist(1));

	}

}
