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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
//import org.springframework.test.context.junit4.SpringRunner;

import com.zup.training.model.Order;
import com.zup.training.model.Product;
import com.zup.training.model.dto.OrderDto;
import com.zup.training.model.dto.OrderItemDto;
import com.zup.training.service.OrderService;
import com.zup.training.service.ProductService;

//@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService prodService;
	
	@Test
	public void testInsertAndDeleteOrder() {
		
		//insert products to Orders test
		Product prd1 = new Product();
		Product prd2 = new Product();
		
		prd1.setSku(1L);
		prd1.setDepth(215.0);
		prd1.setDescription("Painel solar A.1");
		prd1.setHeight(0.52);
		prd1.setManufacturer("Canadian");
		prd1.setPrice(1580.90);
		prd1.setWeight(23.0);
		prd1.setWidth(92.5);
		
		prd2.setSku(2L);
		prd2.setDepth(310.0);
		prd2.setDescription("Painel solar A.2");
		prd2.setHeight(0.67);
		prd2.setManufacturer("BYD");
		prd2.setPrice(1990.90);
		prd2.setWeight(28.0);
		prd2.setWidth(97.5);

		Product p = prodService.insertProduct(prd1);
		assertNotNull(p);
		p = prodService.insertProduct(prd2);
		assertNotNull(p);
		
		//insert orders to test
		OrderDto orderDto = new OrderDto();
		
		orderDto.setClientName("Felipe Alba");
		orderDto.setClientPhone(998877L);
		orderDto.setDiscount(20);
		List<OrderItemDto>  items = new ArrayList<OrderItemDto>();
		items.add(new OrderItemDto(1L, 5));
		items.add(new OrderItemDto(2L, 17));
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
