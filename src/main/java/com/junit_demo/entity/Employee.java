package com.junit_demo.entity;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee105")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int EmployeeId;
	private String EmployeeeName;
	private Date DateOfBirth;
	private Date DateOfJoining;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("employee")
	private Department department;
	

	@OneToOne(targetEntity = Salary.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Salary_Fk", referencedColumnName = "Id")
	private Salary salary;

//	@OneToOne(targetEntity = Attendance.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "Attendance_Fk", referencedColumnName = "Id")
//	private Attendance attendance;

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("employee")
	private List<Attendance> attendance = new LinkedList<Attendance>();

	

	
	

	
	
	
	
}
