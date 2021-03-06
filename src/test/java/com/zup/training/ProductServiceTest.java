package com.zup.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.zup.training.model.Product;
import com.zup.training.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductServiceTest {

	@Autowired
	private ProductService prodService;
	
	@Test
	public void testInsertAndDeleteProduct() {
		
		Product prd = new Product();
		
		prd.setSku(10L);
		prd.setDepth(215.0);
		prd.setDescription("Painel solar A.9");
		prd.setHeight(0.52);
		prd.setManufacturer("Canadian");
		prd.setPrice(1580.90);
		prd.setWeight(23.0);
		prd.setWidth(92.5);
		
		//insert 
		Product p = prodService.insertProduct(prd);
		assertNotNull(p);
		
		Long id = p.getSku();
		assertNotNull(id);
		
		Optional<Product> optProd = prodService.getProductById(id);
		assertTrue(optProd.isPresent());
		
		//get
		prd = optProd.get();
		assertEquals("Canadian", optProd.get().getManufacturer());
		assertEquals("Painel solar A.9", optProd.get().getDescription());
		
		//update
		prd.setDescription("UPDATED");
		Product prdUpdated = new Product();
		try {
			try {
				prdUpdated = prodService.updateProduct(prd, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ObjectNotFoundException e){}
			
		assertEquals("UPDATED", prdUpdated.getDescription());
		
		prdUpdated = prodService.getProductById(id).get();		
		assertEquals("UPDATED", prdUpdated.getDescription());
		
		//delete
		prodService.deleteProductById(id);		
		assertFalse(prodService.getProductById(id).isPresent());
	}
}
