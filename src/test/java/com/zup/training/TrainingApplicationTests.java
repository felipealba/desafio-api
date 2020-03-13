package com.zup.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zup.training.model.Product;
import com.zup.training.service.ProductService;

@SpringBootTest
class TrainingApplicationTests {

	@Autowired
	private ProductService prodService;
	
	@Test
	void testInsert() {
		Product prd = new Product();
		prd.setSku(10L);
		prd.setDepth(215.0);
		prd.setDescription("Painel solar A.3");
		prd.setHeight(0.52);
		prd.setManufacturer("Canadian");
		prd.setPrice(1580.90);
		prd.setWeight(23.0);
		prd.setWidth(92.5);
		
		/*Product pr2 = Product.builder()
				.description("Painel")
				.
		
		Product p = prodService.create(prd);
		assertNotNull(p);
		
		Long id = p.getSku();
		assertNotNull(id);
		
		Optional<Product> optProd = prodService.getProductById(id);
		assertTrue(optProd.isPresent());
		
		prd = optProd.get();
		assertEquals("Canadian", prd.getManufacturer());*/
		
	}

}
