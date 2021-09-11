package com.junit_demo.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.junit_demo.services.DepartmentServices;
import com.junit_demo.entity.Department;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentServices departmentServices;

	@GetMapping("/Department")
	public ResponseEntity<Object> getDepartment() {
		List<Department> departments = departmentServices.getDepartment();
		if (departments.size() == 0) {
			return new ResponseEntity<Object>("Department List Is Empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(departments, HttpStatus.OK);
		}
		// return (ArrayList<Department>) departmentServices.getDepartment();

	}

	@GetMapping("/Department/{Id}")
	public ResponseEntity<Object> getDepartment(@PathVariable int Id) {
 
		boolean isdepaExist = departmentServices.isDepartmentExist(Id);
		if (isdepaExist) {
			Optional<Department> depart =  departmentServices.getDepartment(Id);
			return new ResponseEntity<Object>(depart, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Department Not Found", HttpStatus.NOT_FOUND);
			// throw new NoSuchElementException();
		}

		// return departmentServices.getDepartment(Id);

	}

//	@PostMapping("/Department")
//	public Department addDepartment(@RequestBody Department department)
//	{
//		return departmentServices.addDepartment(department);
//		
//	}
	@DeleteMapping("/Department/{Id}")
	public ResponseEntity<Object> deleteDepartment(@PathVariable int Id) {

		boolean isdepaExist = departmentServices.isDepartmentExist(Id);
		if (isdepaExist) {
			departmentServices.deleteDepartment(Id);
			return new ResponseEntity<Object>(" Deleted Successfully", HttpStatus.OK);
		}  else {
			return new ResponseEntity<Object>("Department Not Found", HttpStatus.NOT_FOUND);
			// throw new NoSuchElementException();
		}

		// departmentServices.deleteDepartment(Id);

	}

	/// only Showing Department

	@GetMapping("/Department/Department")
	public ResponseEntity<Object> getOnlyDepartment() {
		List<Department> departments = departmentServices.getOnlyDepartment();

		if (departments.size() == 0) {
			return new ResponseEntity<Object>("Department List Is Empty", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(departments, HttpStatus.OK);
		}

		// return (ArrayList<Department>) departmentServices.getOnlyDepartment();

	}

	@GetMapping("/Department/Department/{Id}")
	public ResponseEntity<Object> getOnlyDepartment(@PathVariable int Id) {

		boolean isdepaExist = departmentServices.isDepartmentExist(Id);
		if (isdepaExist) {
			Department depart = departmentServices.getOnlyDepartment(Id);
			return new ResponseEntity<Object>(depart, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Department Not Found", HttpStatus.NOT_FOUND);
			// throw new NoSuchElementException();
		}

		// return departmentServices.getOnlyDepartment(Id);

	}

	@PutMapping("/Department/Department/{Id}")
	public ResponseEntity<Object> updateOnlyDepartment(@PathVariable int Id, @RequestBody Department department) {
		boolean isdepaExist = departmentServices.isDepartmentExist(Id);

		if (isdepaExist) {
			departmentServices.updateOnlyDepartment(Id, department);
			return new ResponseEntity<Object>("Department Updated Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Department Not Found", HttpStatus.BAD_REQUEST);
			// throw new NoSuchElementException();
		}

		// return departmentServices.updateOnlyDepartment(Id,department);

	}

}
