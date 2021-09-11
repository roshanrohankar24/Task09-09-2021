package com.junit_demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.junit_demo.entity.Department;
import com.junit_demo.repo.DeprtmentRepo;

@Component
public class DepartmentServices {

	@Autowired
	private DeprtmentRepo deprtmentRepo;

	public ArrayList<Department> getDepartment() {

		return (ArrayList<Department>) deprtmentRepo.findAll();

	}

	public Optional<Department> getDepartment(@PathVariable int Id) {

		return deprtmentRepo.findById(Id);

	}

	public Department addDepartment(@RequestBody Department department) {
		return deprtmentRepo.save(department);

	}

	public void deleteDepartment(@PathVariable int Id) {

		deprtmentRepo.deleteById(Id);

	}

	public Department dto(Department department) {
		Department em = new Department();
		em.setDepartmentId(department.getDepartmentId());
		em.setDepartmentName(department.getDepartmentName());

		return em;

	}

	public List<Department> dto(List<Department> departments) {
		return departments.stream().map(e -> dto(e)).collect(Collectors.toList());

	}

	public ArrayList<Department> getOnlyDepartment() {

		List<Department> dep = deprtmentRepo.findAll();
		return (ArrayList<Department>) dto(dep);

	}

	public Department getOnlyDepartment(int Id) {

		Department dep = deprtmentRepo.findById(Id).orElse(null);
		return dto(dep);

	}

	public Department updateOnlyDepartment(int Id, Department department) {

		Department dep = deprtmentRepo.findById(Id).orElse(null);
		dep.setDepartmentId(department.getDepartmentId());
		dep.setDepartmentName(department.getDepartmentName());
		return deprtmentRepo.save(dep);

	}

	public boolean isDepartmentExist(int id) {

		if (deprtmentRepo.findById(id) == null) {
			return false;
		} else {
			return true;
		}

	}

}
