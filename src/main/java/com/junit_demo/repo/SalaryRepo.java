package com.junit_demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junit_demo.entity.Salary;

public interface SalaryRepo extends JpaRepository<Salary, Integer> {

}
