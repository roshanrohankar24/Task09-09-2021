package com.junit_demo.controller;



import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.junit_demo.entity.ErrorMessage;



@ControllerAdvice
public class EmployeeExceptionController {
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<Object> exception(NoSuchElementException elementException)
	{
		return new ResponseEntity<Object>(" Not Found",HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public ResponseEntity<Object> exception1(EmptyResultDataAccessException accessException)
	{
		
		return new ResponseEntity<Object>("Not Found",HttpStatus.NOT_FOUND);
		
	}
}
