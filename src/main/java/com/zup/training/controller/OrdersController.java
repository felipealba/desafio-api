package com.zup.training.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.training.model.Order;
import com.zup.training.model.dto.OrderDto;
import com.zup.training.service.OrderService;
import com.zup.training.service.ProductService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrderService os;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@PostMapping
	public ResponseEntity<Order> insertOrder(@RequestBody @Valid OrderDto orderDto)
	{
		return ResponseEntity.ok(os.insertOrder(orderDto));
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> listOrd = os.getAllOrders();
		return listOrd.isEmpty() ? 
				ResponseEntity.noContent().build():
				ResponseEntity.ok(listOrd);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(name = "id") Long id){
		Optional<Order> optOrd = os.getOrderById(id);
		
		return optOrd.isPresent()? 
				ResponseEntity.ok(optOrd.get()) :
				ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> uptadeOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable(name = "id") Long id){
		return ResponseEntity.ok(os.updateOrder(orderDto, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOrderById(@PathVariable(name = "id") Long id){
		boolean deleteSucceded = os.deleteOrderById(id);
		if(!deleteSucceded) {
			logger.error("Não foi possível deletar o produto.");
		}
		return ResponseEntity.ok().build();
	}
	
}
