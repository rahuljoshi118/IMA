package com.cg.ima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

	@GetMapping("/all")
	public String allAccess() {
		return "Welcome to Amusement Park";
	}

	@GetMapping("/employee")
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
	public String userAccess() {
		return "Employee Dashboard";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Dashboard";
	}

}
