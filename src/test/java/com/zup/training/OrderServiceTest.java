package com.zup.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zup.training.model.Order;
import com.zup.training.model.dto.OrderDto;
import com.zup.training.model.dto.OrderItemDto;
import com.zup.training.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	public void testInsertAndDeleteOrder() {
		
		OrderDto orderDto = new OrderDto();
		
		orderDto.setClientName("Felipe Alba");
		orderDto.setClientPhone(998877L);
		orderDto.setDiscount(0.20);
		List<OrderItemDto>  items = new ArrayList<OrderItemDto>();
		items.add(new OrderItemDto(8L, 5));
		items.add(new OrderItemDto(9L, 17));
		orderDto.setItems(items);
		
		//insert 
		Order ord = orderService.insertOrder(orderDto);
		assertNotNull(ord);
		
		Long id = ord.getOrderId();
		assertNotNull(id);
		
		Optional<Order> optOrder = orderService.getOrderById(id);
		assertTrue(optOrder.isPresent());
		
		//get
		ord = optOrder.get();
		assertEquals("Felipe Alba", ord.getClientName());
		
		//update
		orderDto.setClientName("NAME_UPDATED");
		Order ordUpdated = new Order();
		try {
			ordUpdated = orderService.updateOrder(orderDto, id);
		} catch (ObjectNotFoundException e){}
			
		assertEquals("NAME_UPDATED", ordUpdated.getClientName());
		
		ordUpdated = orderService.getOrderById(id).get();		
		assertEquals("NAME_UPDATED", ordUpdated.getClientName());
		
		//delete
		orderService.deleteOrderById(id);		
		assertFalse(orderService.getOrderById(id).isPresent());
	}
}
