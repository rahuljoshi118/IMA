package com.cg.ima.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Order;

@Repository				// It is for data CRUD operations and also known as DAO (Data Access Object)
public interface IOrderRepository extends JpaRepository<Order, Integer> {
	
	// Custom JPQL query to find list of order using orderDate
	@Query("from Order where order_date = :orderDate") // JPQL -> table name=Entity class
	List<Order> findByDate(@Param("orderDate") LocalDate orderDate);
	
	
	// Custom JPQL query to find list of order using employeeId
	@Query("from Order where employee_id = :empId") // JPQL -> table name=Entity class
	List<Order>	getAllOrdersByEmployeeId(@Param("empId") int empId);

}
