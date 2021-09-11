package com.junit_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Employee;
import com.junit_demo.repo.AttendanceRepo;
import com.junit_demo.repo.EmployeeRepo;
import com.junit_demo.reportModel.EmployeeReport;
import com.junit_demo.reportModel.EmployeeReport1;
import com.junit_demo.reportModel.ReportConvertor;

@RestController
public class ReportController {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private ReportConvertor convertor;
	@Autowired
	private AttendanceRepo attendanceRepo;

	@GetMapping("/Report")
	public ResponseEntity<Object> getreport() {

		List<Employee> employees = employeeRepo.findAll();

		if (employees.size() == 0) {
			return new ResponseEntity<Object>("Employee Report Is Empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(convertor.dto(employees), HttpStatus.OK);
		}

		// return convertor.dto(employees);
	}

	@GetMapping("/Report/{EmployeeId}")
	public ResponseEntity<Object> getReportById(@PathVariable int EmployeeId) {
		List<Employee> employees = employeeRepo.findAll();
		List<EmployeeReport> employeeReport = (List<EmployeeReport>) convertor.reportById(employees, EmployeeId);
		if (employeeReport.size() != 0) {

			return new ResponseEntity<Object>(employeeReport, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(" Report Not Found", HttpStatus.NOT_FOUND);
			// throw new NoSuchElementException();
		}

		// return (List<EmployeeReport>) convertor.reportById(employees, EmployeeId);

	}

	@GetMapping("/Report1")
	private ResponseEntity<Object> getreport1() {

		List<Attendance> attendances = attendanceRepo.findAll();
		if (attendances.size() == 0) {
			return new ResponseEntity<Object>("Attendance Report Is Empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(convertor.dto1(attendances), HttpStatus.OK);
		}

		// return convertor.dto1(attendances);
	}

	@GetMapping("/Report1/{EmployeeId}")
	public ResponseEntity<Object> getReport1ById(@PathVariable int EmployeeId) {
		List<Attendance> attendances = attendanceRepo.findAll();
		List<EmployeeReport1> employeeReport = (List<EmployeeReport1>) convertor.report1ById(attendances, EmployeeId);
		if (employeeReport.size() != 0) {

			return new ResponseEntity<Object>(employeeReport, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(" Report Not Found", HttpStatus.NOT_FOUND);
			// throw new NoSuchElementException();
		}

		// return (List<EmployeeReport>) convertor.reportById(attendances, EmployeeId);

	}

}
