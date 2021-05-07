package com.cg.ima.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.ima.entities.Order;
import com.cg.ima.exception.OrderNotFoundException;

public interface IOrderService {
	
	public Order addOrder(Order order);
	
	public Order updateOrder(Order order);
	
	public ResponseEntity<String> removeOrder(int orderId);
	
	public Order getOrderById(int orderId) throws OrderNotFoundException;
	
	public List<Order> getAllOrdersByDate(LocalDate orderDate) throws OrderNotFoundException;
	
//	public List<Order> getAllOrders();
	public List<Order> getAllOrdersByEmployeeId(int empId) throws OrderNotFoundException;

}
