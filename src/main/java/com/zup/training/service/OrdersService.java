package com.zup.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.training.model.Order;
import com.zup.training.repository.OrdersRepository;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository ordersRep;
	
	public Order Create(Order order){
		return ordersRep.save(order);
	}
	
}
