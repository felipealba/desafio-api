package com.zup.training.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@PostMapping
	public ResponseEntity<Product> insertProduct(@RequestBody @Valid Product product){
		try {
			Product p = service.insertProduct(product);
			if (p == null) {
				return ResponseEntity.badRequest().build();
			}
			URI location = getUri(p.getSku());
			return ResponseEntity.created(location).build();
		}
		catch (Exception ex){
			logger.error("Não foi possível inserir o produto.");
			return ResponseEntity.badRequest().build();
		}
	}
	
	//product location
	private URI getUri(Long id){
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> list = service.getAllProducts();
		if(list.isEmpty()) {
			logger.info("Não há produtos para serem exibidos. Sem conteúdo.");
		}
		logger.info("Lista de produtos encontrada.");
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> productById(@PathVariable(name = "id") Long id){
		Optional<Product> optProd = service.getProductById(id);
		return (optProd.isPresent() ? 
					ResponseEntity.ok(optProd.get()) :
					ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> uptadeProduct(@RequestBody @Valid Product product, @PathVariable(name = "id") Long id) throws Exception{
		try {
			Product p = service.updateProduct(product, id);
			if (p == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok().build();
		}
		catch (Exception ex){
			logger.error("Não foi possível inserir o produto.");
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable(name = "id") Long id){
		boolean deleteSucceded = service.deleteProductById(id); 
		if(!deleteSucceded) {
			logger.error("Não foi possível deletar o produto.");
		}
		return ResponseEntity.ok().build();
	}

}