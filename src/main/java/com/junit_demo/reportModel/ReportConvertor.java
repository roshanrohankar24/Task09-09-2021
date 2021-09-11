package com.junit_demo.reportModel;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.junit_demo.entity.Attendance;
import com.junit_demo.entity.Employee;
@Component
public class ReportConvertor {
	
	public EmployeeReport dto(Employee employee)
	{
		EmployeeReport em=new EmployeeReport();
		em.setEmployeeId(employee.getEmployeeId());
		em.setEmployeeName(employee.getEmployeeeName());
		em.setDateOfBirth(employee.getDateOfBirth());
		em.setDateOfJoining(employee.getDateOfJoining());
		em.setDepartmentName(employee.getDepartment().getDepartmentName());
		
		return em;
		
	}
	public List<EmployeeReport>dto(List<Employee> employee) {
		return employee.stream().map(e->dto(e)).collect(Collectors.toList());
		
	}
	
	// Report Serach By Id
	
	
	public List<EmployeeReport>reportById(List<Employee> employee,int EmployeeId) {
		return employee.stream().filter(e->e.getEmployeeId()==EmployeeId).map(e->dto(e)).collect(Collectors.toList());
		
	}
	
	
	
	public EmployeeReport1 dto1(Attendance attendance)
	{
		EmployeeReport1 em=new EmployeeReport1();
		em.setEmployeeId("Emp00"+attendance.getEmployee().getEmployeeId());
		em.setEmployeeName(attendance.getEmployee().getEmployeeeName());
//	em.setDepartmentName(attendance.getEmployee().getDepartment().getDepartmentName());
		em.setMonth(attendance.getMonth());
		em.setYear(attendance.getYear());
//		em.setActualSalary(attendance.getEmployee().getSalary().getActualSalry());
	
		
//		int salary=attendance.getEmployee().getSalary().getActualSalry();
		
		
		int year = attendance.getYear();
		int month = attendance.getMonth();
		YearMonth yearMonthObject = YearMonth.of(year, month);
		int daysInMonth = yearMonthObject.lengthOfMonth(); // 28
//		double perDaySalary =salary / daysInMonth;
//		double CalculatedSalary = perDaySalary * attendance.getWorkingDays();

//		em.setCalculatedSalary(CalculatedSalary);
		
		return em;
		
	}
	
	public List<EmployeeReport1>dto1(List<Attendance> attendances) {
		return attendances.stream().map(e->dto1(e)).collect(Collectors.toList());
		
	}
	
	public List<EmployeeReport1>report1ById(List<Attendance> attendances, int EmployeeId) {
		return attendances.stream().filter(e->e.getEmployee().getEmployeeId()==EmployeeId).map(e->dto1(e)).collect(Collectors.toList());
		
	}
	
	
	
	

	public AttendanceReport attendanceReport(Attendance attendance)
	{
		AttendanceReport em=new AttendanceReport();
		em.setEmployeeId(attendance.getEmployee().getEmployeeId());
		em.setAttendanceId(attendance.getId());
		em.setEmployeeName(attendance.getEmployee().getEmployeeeName());
		em.setMonth(attendance.getMonth());
		em.setYear(attendance.getYear());
		em.setWorkingDays(attendance.getWorkingDays());
		
		return em;
		
	}
	
	public List<AttendanceReport> attendanceReport(List<Attendance> attendances,int EmployeeId) {
		return attendances.stream().filter(e->e.getEmployee().getEmployeeId()==EmployeeId).map(e->attendanceReport(e)).collect(Collectors.toList());
		
	}
	

}
