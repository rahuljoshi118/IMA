package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.repository.IEmployeeRepository;

@Service		//It indicates that this class is used to write business logic.
public class EmployeeService implements IEmployeeService {

	@Autowired                //It injects object dependency implicitly.
	private IEmployeeRepository employeeRepository;		//Repository layer of employee entity.
	
	
	//this method is used to add a single employee.
	
	@Override
	public Employee addEmployee(Employee emp) throws EmployeeExistsException {
		
		Employee searchedEmployee = employeeRepository.findByEmployeeEmail(emp.getEmail());

		if (searchedEmployee != null && emp.getEmail().equals(searchedEmployee.getEmail())) 
		{
			throw new EmployeeExistsException("Employee with email '"+emp.getEmail()+"' already exists!");
		}
		else
		{
			Employee employee = employeeRepository.save(emp);
			return employee;
		}

	}
	
	//this method is used to update a single employee.

	@Override
	public Employee updateEmployee(Employee emp) {
		
		Employee employee = employeeRepository.save(emp);
		return employee;
		
	}

	
	//this method is used to delete a single employee.

	@Override
	public ResponseEntity<String> removeEmployee(Long empId) throws EmployeeNotFoundException {
		
		Optional<Employee> searchedEmployee = employeeRepository.findById(empId);

		if (!searchedEmployee.isPresent()) 
		{
			throw new EmployeeNotFoundException("Employee ID '"+ empId +"' Doesn't Exists!");
		}
		else
		{
			employeeRepository.deleteById(empId);
			return ResponseEntity.ok("Employee Id '"+empId+"' has been deleted!");
		}
		
	}

	
	//this method is used to get a single employee.

	@Override
	public Employee getEmployeeById(Long empId) throws EmployeeNotFoundException {
		
		Optional<Employee> emp = employeeRepository.findById(empId);

		if (emp.isPresent()) 
		{
			return emp.get();
		} 
		else 
		{
			throw new EmployeeNotFoundException("Employee ID '"+ empId +"' Not Found!");
		}
		
	}

//if we want all employees using like query	
//	@Override
//	public List<Employee> getAllEmployeesByName(String empName) {
//		
//		List<Employee> emp = employeeRepository.findByName(empName);
//		return emp;
//		
//	}


	
	//this method is used to get a single employee using employee name.

	@Override
	public Employee getEmployeeByName(String empName) throws EmployeeNotFoundException {
		
		Optional<Employee> emp = employeeRepository.findByName(empName);

		if (emp.isPresent()) 
		{
			return emp.get();
		}
		else
		{
			throw new EmployeeNotFoundException("Employee Name '"+ empName +"' Not Found!");
		}
	}

	
	//this method is used to get all employees.

	@Override
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) 
		{	
			throw new EmployeeNotFoundException("No Employee Found!");
		}
		else
		{	
			return employees;
		}
		
	}


}
