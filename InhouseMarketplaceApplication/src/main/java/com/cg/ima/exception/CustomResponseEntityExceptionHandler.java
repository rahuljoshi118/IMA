package com.cg.ima.exception;


import java.time.LocalDate;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;




@RestControllerAdvice		 // object returned is automatically serialized into JSON and passed it to the HttpResponse object.
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private LocalDate localDate = LocalDate.now();
	
	/* 
	 * This method will handle if no employee is found
	 */
	
	@ExceptionHandler({ EmployeeNotFoundException.class })
	public final ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex, WebRequest rq) {
		
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND); //404
		
	}
	
	
	/*
	 * This method will handle if employee already exists 
	 */
	
	@ExceptionHandler({ EmployeeExistsException.class })
	public final ResponseEntity<Object> handleEmployeeExistsException(EmployeeExistsException ex, WebRequest rq) {
		
		ExceptionResponse expResp = new ExceptionResponse("Already Exists!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.CONFLICT); //409
	
	}
	
	
	/*
	 * This method will handle if no order is found 
	 */
	
	@ExceptionHandler({ OrderNotFoundException.class })
	public final ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest rq) {
	
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND); //404
	
	}
	
	
	
	/*
	 * This method will handle if no category is found 
	 */
	
	@ExceptionHandler({ CategoryNotFoundException.class })
	public final ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest rq) {
	
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND); //404
	
	}
	
	
	
	/*
	 * This method will handle if the category already exists 
	 */
	@ExceptionHandler({ CategoryExistsException.class })
	public final ResponseEntity<Object> handlecategoryExistsException(CategoryExistsException ex, WebRequest rq) {
	
		ExceptionResponse expResp = new ExceptionResponse("Already Exists!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.CONFLICT); //409
	
	}
	
	
	
	/*
	 * This method will handle if no resource is found 
	 */
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest rq) {
	
		ExceptionResponse expResp = new ExceptionResponse("Not Found!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expResp, HttpStatus.NOT_FOUND); // 404
	
	}
	
	
	
	/*
	 * This method will handle all type of exceptions with the help of exception class
	 */
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
    
		ExceptionResponse expResp = new ExceptionResponse(ex.getMessage(),"Detail Description of the Exception", localDate);
        return new ResponseEntity<Object>(expResp,HttpStatus.INTERNAL_SERVER_ERROR); // 500
    
	}
	
	
	
	/*
	 * This method will handle if the given input is not valid
	 */
	@ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInvaliInputExceptions(InvalidInputException ex, WebRequest req) {
    
		ExceptionResponse expResp = new ExceptionResponse("Invalid Input!",ex.getMessage(), localDate);
        return new ResponseEntity<Object>(expResp,HttpStatus.INTERNAL_SERVER_ERROR); // 500
    
	}
	
	@Override 	
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,HttpStatus status, WebRequest request) {
		
		ExceptionResponse expRes = new ExceptionResponse("Invalid Argument!", ex.getMessage(), localDate);
		return new ResponseEntity<Object>(expRes, HttpStatus.BAD_REQUEST); // 400
	
	}
	
}
