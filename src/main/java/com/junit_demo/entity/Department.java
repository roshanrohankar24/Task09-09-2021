package com.junit_demo.entity;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Department105")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  int DepartmentId;
	private String  DepartmentName;
	
	@OneToMany(mappedBy = "department",fetch = FetchType.EAGER)
	@JsonIgnoreProperties("department")
	private List<Employee> employee;

	

	
	
}
