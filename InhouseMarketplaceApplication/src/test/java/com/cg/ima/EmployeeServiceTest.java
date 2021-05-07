package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ima.entities.Employee;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.repository.IEmployeeRepository;
import com.cg.ima.service.EmployeeService;



@ExtendWith(MockitoExtension.class)				//to use JUnit extensions from Junit 5
public class EmployeeServiceTest {

	@Mock										//invoke methods of the class that has external communication
	IEmployeeRepository employeeRepository;
	
	
	@InjectMocks								//creates an instance of the class and injects the mocks that are created with the @Mock 
	EmployeeService employeeService;
	
	
	
	/*
		testAddEmployee() is used to test whether the employee details are added successfully. 
	*/
	
	@Test
	public void testAddEmployee() throws EmployeeExistsException
	{
		Employee emp = new Employee();
		emp.setEmpId(10L);
		emp.setUsername("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		assertEquals(emp, employeeService.addEmployee(emp)); //check the expected result with actual result.
	}
	
	
	
	/*
		testUpdateEmployee() is used to test whether the employee detail is updated successfully. 
	*/
	
	@Test
	public void testUpdateEmployee()
	{
		Employee emp = new Employee();
		emp.setEmpId(10L);
		emp.setUsername("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		emp.setEmail("rahuljoshi@gmail.com");
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);
		
		assertEquals(emp,employeeService.updateEmployee(emp));
		
	}
	
	
	
	/*
		testDeleteCategory() is used to test whether the employee details are deleted successfully. 
	*/
	
	@Test
	public void testDeleteEmployee()
	{
		Employee emp = new Employee();
		emp.setEmpId(10L);
		emp.setUsername("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		
		Mockito.when(employeeRepository.existsById(emp.getEmpId())).thenReturn(false);
	    assertFalse(employeeRepository.existsById(emp.getEmpId()));
	}
	
	
	
	/*
		testGetEmployeeById() is used to test whether the correct employee details are obtained with the respective given employee id.
	*/

	@Test
	public void testGetEmployeeById() throws EmployeeNotFoundException
	{
		Employee emp = new Employee();
		emp.setEmpId(10L);
		emp.setUsername("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		Mockito.when(employeeRepository.findById(10L)).thenReturn(Optional.of(emp));
		Employee actualEmp = employeeService.getEmployeeById(10L);
		assertEquals(10,actualEmp.getEmpId());
	}


	
	/*
		testGetEmployeeByName() is used to test whether the correct employee details are obtained with the respective given employee name.
	*/
	
	@Test
	public void testGetEmployeeByName() throws EmployeeNotFoundException
	{
		Employee emp = new Employee();
		emp.setEmpId(10L);
		emp.setUsername("Chandan Hayaran");
		emp.setMobileNumber("7748064046");
		emp.setEmail("chandanhayaran@gmail.com");
		emp.setLocation("Gwalior");
		
		Mockito.when(employeeRepository.findByName("Chandan Hayaran")).thenReturn(Optional.of(emp));
		Employee actualEmployee = employeeService.getEmployeeByName("Chandan Hayaran");
		assertEquals("Chandan Hayaran",actualEmployee.getUsername());
	}
	

	
	/*
		testGetAllEmployees() is used to test whether the correct error message is shown if no employee details is provided.
	*/
	
	@Test
	public void testGetAllEmployees()
	{
		List<Employee> employees = new ArrayList<>();
		Mockito.when(employeeRepository.findAll()).thenReturn(employees);
		Exception ex = assertThrows(EmployeeNotFoundException.class, ()-> employeeService.getAllEmployees());//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Employee Found!", ex.getMessage());
		
	}
}
