package com.cg.ima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Employee;

@Repository			// It is for data CRUD operations and also known as DAO (Data Access Object)
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
	
	// Custom JPQL query to find employee name
	@Query("from Employee where employee_name like %:empName%") // JPQL -> table name=Entity class
	Optional<Employee> findByName(@Param("empName") String empName);
	//	List<Employee> findByName(@Param("empName") String empName);			//to find all the employees using the above query	
	
	
	// Custom JPQL query to find employee using email
	@Query("from Employee where email = :empEmail")
	Employee findByEmployeeEmail(@Param("empEmail") String empEmail);
	
	
	Optional<Employee> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
