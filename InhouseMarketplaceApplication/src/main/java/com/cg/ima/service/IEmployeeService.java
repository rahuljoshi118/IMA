package com.cg.ima.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;

public interface IEmployeeService {
	
	public Employee addEmployee(Employee emp) throws EmployeeExistsException;
	
	public Employee updateEmployee(Employee emp);
	
	public ResponseEntity<String> removeEmployee(Long empId) throws EmployeeNotFoundException;
	
	public Employee getEmployeeById(Long empId) throws EmployeeNotFoundException;
	
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException;

//	public List<Employee> getAllEmployeesByName(String empName);
	public Employee getEmployeeByName(String empName) throws EmployeeNotFoundException;

}
