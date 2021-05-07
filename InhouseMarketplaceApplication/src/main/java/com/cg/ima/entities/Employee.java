package com.cg.ima.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity						// It defines that a class can be mapped to a database table
@Table(name = "employee")	// user define table name
public class Employee {

	@Id		// define primary key
	@Column(name = "employee_id")		// define column name
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name = "employee_seq",sequenceName = "employee_seq", allocationSize=1)
	private Long empId;

//	@Column(name = "employee_name")
//	@Size(min = 5, max = 20, message="Employee name should contains minimum 5 and maximum 20 characters.")		// for validation
//	private String empName;


	private String username;
	
	

	@Column(length = 3000)
	private String password;
	
	
	@Column(name = "mobile_number")
	@Size(min = 10, max = 10, message="Mobile number must be 10 digits.")		// for validation
	@Pattern(regexp = "^[6-9]{1}\\d{9}$", message="Mobile number must start from 6, 7, 8, 9.")		// for validation
	private String mobileNumber;

	@Column(name = "email")
	@Email(message = "Email should be valid.")		// for validation
	private String email;
	
	@Column(name = "location")
	private String location;
	
	
	//	String name = null; (@NotNull: false / @NotEmpty: false / @NotBlank: false)
	//	String name = ""; (@NotNull: true / @NotEmpty: false / @NotBlank: false)
	//	String name = " "; (@NotNull: true / @NotEmpty: true / @NotBlank: false)

	
	//Bi-directional OneToMany Relation
	
	@JsonIgnore	// It is used at field level to mark a property or list of properties to be ignored.
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employeeOrder", cascade = CascadeType.REMOVE)
	private List<Order> orders;
	

	/*
	 * (mappedBy) signals hibernate that the key for the relationship is on the other side.
	 * (FetchType.EAGER) will retrieve everything
	 * (FetchType.LAZY) will retrieve on request
	 * (CascadeType.REMOVE) will remove data from related table also.
	 */
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_roles", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	//default constructor of class Employee
	
	public Employee() {
		super();
	}


	public Employee(Long empId,
			@NotBlank(message = "Username is mandatory") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long") @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces") String username,
			@NotBlank @Size(max = 120) String password,
			@Size(min = 10, max = 10, message = "Mobile number must be 10 digits.") @Pattern(regexp = "^[6-9]{1}\\d{9}$", message = "Mobile number must start from 6, 7, 8, 9.") String mobileNumber,
			@Email(message = "Email should be valid.") String email,
			@NotBlank(message = "Location cannot be empty.") String location, List<Order> orders) {
		super();
		this.empId = empId;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.location = location;
		this.orders = orders;
	}
	
	




	public Employee(String username, String password, @Email(message = "Email should be valid.") String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public Long getEmpId() {
		return empId;
	}


	public void setEmpId(Long empId) {
		this.empId = empId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	

	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", username=" + username + ", password=" + password + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", location=" + location + ", orders=" + orders + ", roles="
				+ roles + "]";
	}


	
	
	//parameterized constructor of class Employee
	
	
	
	
	
}

