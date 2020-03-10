	package com.zup.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Double weight;
	private Double height;
	private Double width;
	private Double depth;
	private Double price;
	private String manufacturer;
}