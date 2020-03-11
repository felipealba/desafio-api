package com.zup.training.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.zup.training.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private String clientName;
	private Long clientPhone;
	
	@ManyToMany
	@JoinTable(
			name = "order_and_product",
			joinColumns = @JoinColumn(name = "orderId"),
			inverseJoinColumns = @JoinColumn(name = "productId")
	)
	private List<Product> productList;
	
	private Double totalPrice;
	private Double discount;
	private Double orderFinalPrice;
		
}
