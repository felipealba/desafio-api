	package com.zup.training.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.zup.training.model.Order;

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
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sku;
	private String description;
	@NotNull
	private Double weight;
	private Double height;
	private Double width;
	private Double depth;
	private Double price;
	private String manufacturer;
	
	@ManyToMany(mappedBy = "productList")
	private List<Order> orders;
}