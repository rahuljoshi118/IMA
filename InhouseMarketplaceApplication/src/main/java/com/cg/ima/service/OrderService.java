package com.cg.ima.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cg.ima.entities.Order;
import com.cg.ima.exception.OrderNotFoundException;
import com.cg.ima.repository.IOrderRepository;

@Service		//It indicates that this class is used to write business logic.
public class OrderService implements IOrderService {

	@Autowired                //It injects object dependency implicitly.
	private IOrderRepository orderRepository;		//Repository layer of order entity.
	
	
	//this method is used to add a single order.
	
	@Override
	public Order addOrder(Order order) {
		
		Order o = orderRepository.save(order);
		return o;

	}

	
	//this method is used to update a single order.
	
	@Override
	public Order updateOrder(Order order) {
		
		Order o = orderRepository.save(order);
		return o;
		
	}

	
	//this method is used to delete a single order.

	@Override
	public ResponseEntity<String> removeOrder(int orderId) {
		System.out.println("removeOrder service");
		orderRepository.deleteById(orderId);
		return ResponseEntity.ok("Order deleted!");
		
	}
	
	
	//this method is used to get a single order.

	@Override
	public Order getOrderById(int orderId) throws OrderNotFoundException {
		
		Optional<Order> order = orderRepository.findById(orderId);

		if (order.isPresent()) 
		{
			return order.get();
		} 
		else 
		{
			throw new OrderNotFoundException("Order ID '"+ orderId +"' Not Found!");
		}
		
	}

	
	//this method is used to get all orders using order date.
	
	@Override
	public List<Order> getAllOrdersByDate(LocalDate orderDate) throws OrderNotFoundException {
		
		List<Order> orders = orderRepository.findByDate(orderDate);
		if (orders.isEmpty()) 
		{
			throw new OrderNotFoundException("Order On Date '"+ orderDate +"' Not Found!");
		}
		else
		{	
			return orders;
		}
		
	}



//	@Override
//	public List<Order> getAllOrders() {
//		
//		return orderRepository.findAll();
//		
//	}

	
	//this method is used to get all orders using employee id.
	
	@Override
	public List<Order> getAllOrdersByEmployeeId(int empId) throws OrderNotFoundException {
		
		List<Order> orders = orderRepository.getAllOrdersByEmployeeId(empId);
		if (orders.isEmpty()) 
		{
			throw new OrderNotFoundException("No Order Found!");
		}
		else
		{	
			return orders;
		}
		
	}


}
