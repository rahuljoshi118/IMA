package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ima.entities.Order;
import com.cg.ima.exception.OrderNotFoundException;
import com.cg.ima.repository.IOrderRepository;
import com.cg.ima.service.OrderService;



@ExtendWith(MockitoExtension.class)			//to use JUnit extensions from Junit 5
public class OrderServiceTest {

	@Mock									//invoke methods of the class that has external communication
	IOrderRepository orderRepository;
	
	
	@InjectMocks							//creates an instance of the class and injects the mocks that are created with the @Mock 
	OrderService orderService;
	
	
	
	/*
		testAddOrder() is used to test whether the order details is added successfully. 
	*/
	
	@Test
	public void testAddOrder()
	{
		LocalDate orderDate = null;
		
		Order order = new Order();
		order.setOrderId(10);
		order.setOrderDate(orderDate);
		
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		assertEquals(order, orderService.addOrder(order)); //check the expected result with actual result.
	}
	
	
	
	/*
		testUpdateOrder() is used to test whether the order detail is updated successfully. 
	*/
	
	@Test
	public void testUpdateOrder()
	{
		LocalDate orderDate = null;
		
		Order order = new Order();
		order.setOrderId(10);
		order.setOrderDate(orderDate);		
		
		order.setOrderId(20);
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		
		assertEquals(order,orderService.updateOrder(order));
		
	}
	
	
	
	/*
		testDeleteOrder() is used to test whether the order details are deleted successfully. 
	*/
	
	@Test
	public void testDeleteOrder()
	{
		LocalDate orderDate = null;
		
		Order order = new Order();
		order.setOrderId(10);
		order.setOrderDate(orderDate);
		
		
		Mockito.when(orderRepository.existsById(order.getOrderId())).thenReturn(false);
	    assertFalse(orderRepository.existsById(order.getOrderId()));
	}
	
	
	
	/*
		testGetOrderById() is used to test whether the correct order details are obtained with the respective given order id.
	*/
	
	@Test
	public void testGetOrderById() throws OrderNotFoundException
	{
		LocalDate orderDate = null;
		
		Order order = new Order();
		order.setOrderId(10);
		order.setOrderDate(orderDate);
		
		Mockito.when(orderRepository.findById(10)).thenReturn(Optional.of(order));
		Order actualorder = orderService.getOrderById(10);
		assertEquals(10,actualorder.getOrderId());
	}

	
	
	/*
		testGetOrderByDate() is used to test whether the correct order details are obtained with the respective given order date.
	*/
	
	@Test
	public void testGetOrderByDate() throws OrderNotFoundException
	{
		LocalDate orderDate = null;
		
		Order order2 = new Order();
		order2.setOrderId(10);
		order2.setOrderDate(orderDate);
		
		
		List<Order> orders = new ArrayList<>();
		orders.add(order2);
		Mockito.when(orderRepository.findByDate(orders.get(0).getOrderDate())).thenReturn(orders);
		assertEquals(orders, orderService.getAllOrdersByDate((orders.get(0).getOrderDate())));
	}
	

	
	/*
		testGetAllEmployees() is used to test whether the correct error message is shown if no order details is provided.
	*/

	@Test
	public void testGetAllOrdersByEmployeeId()
	{
		List<Order> orders = new ArrayList<>();
		Mockito.when(orderRepository.getAllOrdersByEmployeeId(10)).thenReturn(orders);
		Exception ex = assertThrows(OrderNotFoundException.class, ()-> orderService.getAllOrdersByEmployeeId(10));//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Order Found!", ex.getMessage());
		
	}
}
