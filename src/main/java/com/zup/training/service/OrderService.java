package com.zup.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	public Order Create(OrderDto orderDto)
	{
		
		Order order = new Order();
		double totalPrice=0.0;
		
		order.setClientName(orderDto.getClientName());
		order.setClientPhone(orderDto.getClientPhone());
		order.setItems(new ArrayList<OrderItem>());
		order.setDiscount(orderDto.getDiscount());
		order = ordersRep.save(order); //Pq salvar em banco aqui se já salva mais abaixo?
		
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
		order.setOrderFinalPrice(totalPrice*(1-orderDto.getDiscount()));
		
		return ordersRep.save(order);
		
	}
	
	public List<Order> listAll() {
		return ordersRep.findAll();
	}
	
	public Optional<Order> getOrderById(Long id) {
		return ordersRep.findById(id);
	}
	
	public Order update(OrderDto orderDto, Long id) {
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
			order = ordersRep.save(order); //Pq salvar em banco aqui se já salva mais abaixo?
			
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
			
			/*orderDb.setClientName(order.getClientName());
			orderDb.setClientPhone(order.getClientPhone());
			orderDb.setDiscount(order.getDiscount());			
			orderDb.setOrderId(order.getOrderId());*/
			
			//tirar duvida sobre o Builder abaixo. Tentei colocar aqui mas ele eh estatico. Qual solucao?
			
			/*orderDb = Order.builder()
						.clientName(order.getClientName())
						.clientPhone(order.getClientPhone())
						.discount(order.getDiscount())
						.orderFinalPrice(order.getOrderFinalPrice())
						.orderId(order.getOrderId())
						.totalPrice(order.getTotalPrice());*/
		}
		else
		{
			throw new RuntimeException("Não foi possível atualizar o registro");		
		}
		
		
	}

	public Optional<Order> deleteOrder(Long id) 
	{
		Assert.notNull(id, "Não foi possível atualizar o registro.");
		
		//Obtem o Order do banco de dados
		Optional<Order> optOrderDb = this.getOrderById(id);
		if(optOrderDb.isPresent()) 
		{
			Order order = optOrderDb.get();
			/*for(OrderItem item : order.getItems()) 
			{
				ordersItemRep.delete(item);
			}*/
			
			//ordersItemRep.deleteAll(order.getItems());
			ordersRep.delete(order);
			return optOrderDb;
		}
		else
		{
			throw new RuntimeException("Não foi possível deletar o registro");		
		}
	}
}
