	package com.zup.training.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Campo 'Peso' não pode estar em branco.")
	private Double weight;
	@NotNull(message = "Campo 'Altura' não pode estar em branco.")
	private Double height;
	@NotNull(message = "Campo 'Largura' não pode estar em branco.")
	private Double width;
	@NotNull(message = "Campo 'Tamanho' não pode estar em branco.")
	private Double depth;
	@NotNull(message = "Campo 'Preço' não pode estar em branco.")
	private Double price;
	@NotNull(message = "Campo 'fabricante' não pode estar em branco.")
	@NotBlank(message = "Campo 'fabricante' não pode estar vazio")
	private String manufacturer;
	@NotNull
	private Date startAvailability;
	@NotNull
	private Date endAvailability;

}