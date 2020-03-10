package com.zup.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.training.model.Product;
import com.zup.training.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public Product create(Product product) {
		return repository.save(product);
	}

	public List<Product> listAll() {		
		return repository.findAll();
	}

	
}