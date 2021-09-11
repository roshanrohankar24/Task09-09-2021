package com.junit_demo.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Salary105")
public class Salary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	private int  ActualSalry;
	

}
