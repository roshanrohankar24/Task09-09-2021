package com.junit_demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.junit_demo.entity.Attendance;
import com.junit_demo.repo.AttendanceRepo;
import com.junit_demo.reportModel.AttendanceReport;
import com.junit_demo.reportModel.ReportConvertor;

@Component
public class AttendanceServices {
	@Autowired
	private AttendanceRepo attendanceRepo;



	@Autowired
	private ReportConvertor convertor;

	public ArrayList<Attendance> getAttendance() {

		return (ArrayList<Attendance>) attendanceRepo.findAll();

	}

	public List<AttendanceReport> getAttendance(@PathVariable int EmployeeId) {

		List<Attendance> attendances = (List<Attendance>) attendanceRepo.findAll();
		return convertor.attendanceReport(attendances, EmployeeId);

	}

	public Attendance addAttendance(@RequestBody Attendance attendance) {
		return attendanceRepo.save(attendance);

	}

	public void deleteAttendance(@PathVariable int Id) {

		attendanceRepo.deleteById(Id);

	}

	public boolean isAttendanceExist(int Id) {

		if (attendanceRepo.findById(Id) == null) {
			return false;
		} else {
			return true;
		}
	}

}
