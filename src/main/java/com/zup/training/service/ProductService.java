package com.zup.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.zup.training.model.Product;
import com.zup.training.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public Product insertProduct(Product product) {
		return repository.save(product);
	}

	public List<Product> getAllProducts() {		
		return repository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		
		Optional<Product> optPrd = repository.findById(id);
		
		Assert.isTrue(optPrd.isPresent(), "Não foi possível encontrar producto com Id informado");
			
		return optPrd;
	}
	
	public Product updateProduct(Product product, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro.");
		
		//Obtem o Product do banco de dados
		Optional<Product> optProductDb = getProductById(id);
		if(optProductDb.isPresent()) {
			Product productDb = optProductDb.get();
			
			productDb.setDepth(product.getDepth());
			productDb.setDescription(product.getDescription());
			productDb.setHeight(product.getHeight());
			productDb.setManufacturer(product.getManufacturer());
			productDb.setPrice(product.getPrice());
			productDb.setWeight(product.getWeight());
			productDb.setWidth(product.getWidth());
			
			return repository.save(productDb);
		}
		else
		{
			throw new RuntimeException("Não foi possível atualizar o registro");		
		}
		
		
	}

	public Optional<Product> deleteProductById(Long id) {
		Assert.notNull(id, "Não foi possível deletar o registro.");
		
		//Obtem o Product do banco de dados para delecao
		Optional<Product> optProductDb = getProductById(id);
		if(optProductDb.isPresent()) {
			repository.deleteById(id);
			return optProductDb;
		}
		else
		{
			throw new RuntimeException("Não foi possível deletar o registro. Motivo: registro nao encontrado");		
		}
	}

	

	
}