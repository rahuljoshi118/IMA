package com.cg.ima.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Employee;
import com.cg.ima.entities.Order;
import com.cg.ima.exception.EmployeeExistsException;
import com.cg.ima.exception.EmployeeNotFoundException;
import com.cg.ima.exception.InvalidInputException;
import com.cg.ima.exception.OrderNotFoundException;
import com.cg.ima.service.EmployeeService;
import com.cg.ima.service.OrderService;

@RestController               //It is used to create RESTful web services using Spring MVC, Once response body is generated from the handler method, it converts it to JSON or XML response.
@RequestMapping("/ima")       //This maps HTTP requests to handler methods of MVC and REST controllers.
public class EmployeeController {
	
	@Autowired				  					//It injects object dependency implicitly.
	private EmployeeService employeeService;	//Service layer of employee entity.
	
	
	@Autowired				  					//It injects object dependency implicitly.
	private OrderService orderService;			//Service layer of order entity.

	
	
// if we want to post(insert) multiple employees	
//	@PostMapping(path = "/employee")
//	public List<Employee> addEmployee(@Valid @RequestBody List<Employee> employees) {
//		
//		for(Employee e: employees)
//		{
//			Employee emp = employeeService.addEmployee(e);
//		}		
//		return employees;
//		
//	}

	
	
	// if we want to post(insert) a single employee
	
	@PostMapping(path = "/employee")		// http://localhost:8000/ima/employee
	public Employee addEmployee(@Valid @RequestBody Employee emp, BindingResult bindingResult) throws InvalidInputException, EmployeeExistsException {
	
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Employee employee = employeeService.addEmployee(emp);		// calls service layer addEmployee() method.
		return employee;
		
	}

	
	
	// if we want to put(update) a single employee
	
	@PutMapping(path = "/employee")			// http://localhost:8000/ima/employee
	public Employee updateEmployee(@Valid @RequestBody Employee emp, BindingResult bindingResult) throws InvalidInputException {
		
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Employee employee = employeeService.updateEmployee(emp);		// calls service layer updateEmployee() method.
		return employee;
		
	}

	
	
	// if we want to delete(remove) a single employee
	
	@DeleteMapping(path = "/employee/{empId}")		// http://localhost:8000/ima/employee/10
	public ResponseEntity<String> removeEmployee(@PathVariable("empId") Long empId) throws EmployeeNotFoundException {
		
		return employeeService.removeEmployee(empId);		// calls service layer removeEmployee() method.

	}

	
	
	// if we want to get(fetch) a single employee
	
	@GetMapping(path = "/employee/{empId}")			// http://localhost:8000/ima/employee/10
	public Employee getEmployeeById(@PathVariable("empId") Long empId) throws EmployeeNotFoundException{
	
		Employee employee = employeeService.getEmployeeById(empId);		// calls service layer getEmployeeById() method.
		return employee;
		
	}
	
	

//if we want all employees using like query	
//	@GetMapping(path = "/employee/name/{empName}")
//	public List<Employee> getAllEmployeesByName(@PathVariable("empName") String empName) {
//		
//		List<Employee> employees = employeeService.getAllEmployeesByName(empName);
//		return employees;
//	
//	}

	
	
	// if we want to get(fetch) a single employee by their full name	
	
	@GetMapping(path = "/employee/name/{empName}")			// http://localhost:8000/ima/employee/Chandan
	public Employee getEmployeeByName(@PathVariable("empName") String empName) throws EmployeeNotFoundException {
		
		Employee employee = employeeService.getEmployeeByName(empName);		// calls service layer getEmployeeByName() method.
		return employee;
		
	}

	
	
	// if we want to get(fetch) all employees
	
	@GetMapping(path = "/employee")			// http://localhost:8000/ima/employee
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		
		List<Employee> employees = employeeService.getAllEmployees();		// calls service layer getAllEmployees() method.
		return employees;
		
	}
	
	
	
	
	
	
	
//Order Controller Start	
	
	// if we want to post(insert) a single order
	
	@PostMapping(path = "/employee/order")		// http://localhost:8000/ima/employee/order
	public Order addOrder(@RequestBody Order order) {
	
		Order o = orderService.addOrder(order);		// calls service layer addOrder() method.
		return o;
		
	}
	
	
	
//	@PutMapping(path = "employee/order")
//	public Order updateOrder(@RequestBody Order order) {
//		
//		Order o = orderService.updateOrder(order);
//		return o;
//		
//	}
	
	
	
	// if we want to delete(remove) a single order
	
	@DeleteMapping(path = "/employee/order/{orderId}")		// http://localhost:8000/ima/employee/order/10
	public ResponseEntity<String> removeOrder(@PathVariable("orderId") int orderId) {
		
		return orderService.removeOrder(orderId);			// calls service layer removeOrder() method.

	}
	
	
	
	// if we want to get(fetch) a single order
	
	@GetMapping(path = "/employee/order/{orderId}")			// http://localhost:8000/ima/employee/order/10
	public Order getOrderById(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
	
		Order order = orderService.getOrderById(orderId);		// calls service layer getOrderById() method.
		return order;
		
	}
	
	

	//if we want to get(fetch) all orders by their date using custom like query
	
	@GetMapping(path = "/employee/order/date/{orderDate}")		// http://localhost:8000/ima/employee/order/2021-04-19
	public List<Order> getAllOrdersByDate(@PathVariable("orderDate")@DateTimeFormat(iso = ISO.DATE) LocalDate orderDate) throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrdersByDate(orderDate);		// calls service layer getAllOrdersByDate() method.
		return orders;
	
	}
	
	
	
	
//	@GetMapping(path = "employee/order")
//	public List<Order> getAllOrders() throws OrderNotFoundException {
//		
//		List<Order> orders = orderService.getAllOrders();
//		if (orders.isEmpty()) 
//		{
//			throw new OrderNotFoundException("No Order Found!");
//		}
//		else
//		{	
//			return orders;
//		}
//	}
	
	
	
	// if we want to get(fetch) all orders using empid
	
	@GetMapping(path = "employee/order/empid/{empId}")			// http://localhost:8000/ima/employee/order/empid/10
	public List<Order> getAllOrdersByEmployeeId(@PathVariable("empId") int empId) throws OrderNotFoundException {
		
		List<Order> orders = orderService.getAllOrdersByEmployeeId(empId);		// calls service layer getAllOrdersByEmployeeId() method.
		return orders;
		
	}

	
	
//Order Controller End	
	
	
}
