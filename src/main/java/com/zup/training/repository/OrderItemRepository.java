package com.zup.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.training.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
