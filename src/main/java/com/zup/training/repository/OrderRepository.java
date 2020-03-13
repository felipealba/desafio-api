package com.zup.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.training.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
