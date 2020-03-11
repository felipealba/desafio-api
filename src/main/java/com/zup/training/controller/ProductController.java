package com.zup.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.training.model.Product;
import com.zup.training.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<Product> insertProduct (@RequestBody @Valid Product product){
		return ResponseEntity.ok(service.create(product));
	}
	
	@GetMapping("/hello")
	public String Hello (){
		return "Hello product controller";
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct (){
		return ResponseEntity.ok(service.listAll());
	}
	
	/*@PutMapping
	public ResponseEntity<Product> uptadeProduct (@RequestBody @Valid Product product){
		return ResponseEntity.ok(repository.save(product));
	}
	
	@DeleteMapping
	public ResponseEntity<Product> deleteProduct (@RequestBody @Valid Product product){
		return ResponseEntity.ok(repository.delete(product));
	}*/

}