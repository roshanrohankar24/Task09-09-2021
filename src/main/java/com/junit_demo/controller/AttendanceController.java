package com.junit_demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.junit_demo.entity.*;
import com.junit_demo.services.AttendanceServices;

import com.junit_demo.reportModel.AttendanceReport;

@RestController
public class AttendanceController {

	@Autowired
	private AttendanceServices attendanceServices;

	@GetMapping("/Attendance")
	public ResponseEntity<Object> getAttendance() {
		ArrayList<Attendance> attendance = attendanceServices.getAttendance();
		if (attendance.size() == 0) {
			return new ResponseEntity<Object>("Employee List Is Empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(attendance, HttpStatus.OK);
		}

		// return (ArrayList<Attendance>) attendanceServices.getAttendance();

	}

	@GetMapping("/Attendance/{EmployeeId}")
	public ResponseEntity<Object> getAttendance(@PathVariable int EmployeeId) {

		boolean isAttendanceExist = attendanceServices.isAttendanceExist(EmployeeId);
		if (isAttendanceExist) {
			List<AttendanceReport> report = attendanceServices.getAttendance(EmployeeId);
			return new ResponseEntity<Object>(report, HttpStatus.OK);
		} else {
			 return new ResponseEntity<Object>("Attendance Not Found",HttpStatus.NOT_FOUND);
			//throw new NoSuchElementException();
		}

		// return attendanceServices.getAttendance(EmployeeId);

	}

//	@PostMapping("/Attendance")
//	public ResponseEntity<Object> addAttendance(@RequestBody Attendance attendance)
//	{
//		
//		 attendance= attendanceServices.addAttendance(attendance);
//			return new ResponseEntity<Object>("Employee Created Successfully"+attendance.getId(),HttpStatus.CREATED);
//		  
//		
//	//	return attendanceServices.addAttendance(attendance);
//		
//	}
	@DeleteMapping("/Attendance/{Id}")
	public ResponseEntity<Object> deleteAttendance(@PathVariable int Id) {

		boolean isAttendanceExist = attendanceServices.isAttendanceExist(Id);
		if (isAttendanceExist) {
			attendanceServices.deleteAttendance(Id);
			return new ResponseEntity<Object>(" Deleted Successfully", HttpStatus.OK);
		} else {
			 return new ResponseEntity<Object>("Attendance Not Found",HttpStatus.NOT_FOUND);
			//throw new NoSuchElementException();
		}
		// attendanceServices.deleteAttendance(Id);

	}
}
