package com.zup.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.training.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}