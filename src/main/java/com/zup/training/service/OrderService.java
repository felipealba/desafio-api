package com.zup.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zup.training.model.Order;
import com.zup.training.model.OrderItem;
import com.zup.training.model.Product;
import com.zup.training.model.dto.OrderDto;
import com.zup.training.model.dto.OrderItemDto;
import com.zup.training.repository.OrderItemRepository;
import com.zup.training.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository ordersRep;
	
	@Autowired
	private OrderItemRepository ordersItemRep;
	
	@Autowired
	private ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public Order insertOrder(OrderDto orderDto)
	{
		Order order = new Order();
		double totalPrice=0.0;
		
		order.setClientName(orderDto.getClientName());
		order.setClientPhone(orderDto.getClientPhone());
		order.setItems(new ArrayList<OrderItem>());
		order.setDiscount(orderDto.getDiscount());
		order = ordersRep.save(order);
		
		for(OrderItemDto item : orderDto.getItems()) {
			
			Product prd = productService.getProductById(item.getProductId()).get();
			totalPrice += (prd.getPrice()*item.getQuantity());
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(prd);
			orderItem.setQuantity(item.getQuantity());
			ordersItemRep.save(orderItem);
			order.getItems().add(orderItem);
			
		}
		
		order.setTotalPrice(totalPrice);
		order.setOrderFinalPrice(totalPrice*(1-(orderDto.getDiscount()/100)));
		
		return ordersRep.save(order);
		
	}
	
	public List<Order> getAllOrders() {
		return ordersRep.findAll();
	}
	
	public Optional<Order> getOrderById(Long id) {
		Optional<Order> opt = ordersRep.findById(id);
		if(!opt.isPresent()) {
			logger.error("Não foi possível encontrar pedido com o Id informado.");
		}
		return opt;
	}
	
	public Order updateOrder(OrderDto orderDto, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro.");
		
		//Obtem o Order do banco de dados
		Optional<Order> optOrderDb = this.getOrderById(id);
		if(optOrderDb.isPresent()) 
		{			
			Order order = new Order();
			double totalPrice=0.0;
			
			order.setClientName(orderDto.getClientName());
			order.setClientPhone(orderDto.getClientPhone());
			order.setItems(new ArrayList<OrderItem>());
			order.setDiscount(orderDto.getDiscount());
			order.setOrderId(id);
			order = ordersRep.save(order);
			
			for(OrderItemDto item : orderDto.getItems()) 
			{
				Product prd = productService.getProductById(item.getProductId()).get();
				totalPrice += (prd.getPrice()*item.getQuantity());
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(order);
				orderItem.setProduct(prd);
				orderItem.setQuantity(item.getQuantity());
				ordersItemRep.save(orderItem);
				order.getItems().add(orderItem);
				
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderFinalPrice(totalPrice*(1-orderDto.getDiscount()));
			
			return ordersRep.save(order);
		}
		else
		{
			logger.error("Não foi possível atualizar o pedido.");
			throw new RuntimeException("Não foi possível atualizar o registro");		
		}
	}

	public boolean deleteOrderById(Long id) 
	{
		Assert.notNull(id, "Não foi possível atualizar o registro.");
		
		//Obtem o Order do banco de dados
		Optional<Order> optOrderDb = this.getOrderById(id);
		if(optOrderDb.isPresent()) 
		{
			Order order = optOrderDb.get();
			ordersRep.delete(order);
			return true;
		}
		else
		{
			throw new RuntimeException("Não foi possível deletar o registro");		
		}
	}
}
