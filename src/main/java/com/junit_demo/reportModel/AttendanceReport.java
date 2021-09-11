package com.junit_demo.reportModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceReport {
	
	private int employeeId;
	private int attendanceId;
	private String employeeName;
	private int month;
	private int year;
	private int workingDays;
	

	

}
