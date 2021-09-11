package com.junit_demo.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.junit_demo.entity.Department;
public interface DeprtmentRepo extends JpaRepository<Department, Integer> {
	

}
