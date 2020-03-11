package com.zup.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.training.model.Order;
import com.zup.training.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService os;
	
	@PostMapping
	public ResponseEntity<Order> Create(@RequestBody @Valid Order order)
	{
		return ResponseEntity.ok(os.Create(order));
	}
	
}
