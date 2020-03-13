package com.zup.training.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zup.training.model.Product;
import com.zup.training.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<Product> insertProduct (@RequestBody @Valid Product product){
		try {
			Product p = service.create(product);
			URI location = getUri(p.getSku());
			return ResponseEntity.created(location).build();
		}
		catch (Exception ex){
			return ResponseEntity.badRequest().build();
		}
			
	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> productById(@PathVariable(name = "id") Long id){
		Optional<Product> optProd = service.getProductById(id);
		return (optProd.isPresent() ? 
					ResponseEntity.ok(optProd.get()) :
					ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> uptadeProduct(@RequestBody @Valid Product product, @PathVariable(name = "id") Long id){
		return ResponseEntity.ok(service.update(product, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(service.deleteProduct(id).get());
	}

}