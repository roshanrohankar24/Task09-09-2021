package com.junit_demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.junit_demo.entity.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
//	@Query(value="select e.employeee_name,e.employee_id,e.date_of_birth,e.date_of_joining ,e.department_department_id,d.department_name from employee105 e join department105 d",nativeQuery = true)
//     ArrayList<Employee> report() ;
}
//select e.employeee_name,e.employee_id ,e.date_of_joining,e.date_of_birth,d.department_name

