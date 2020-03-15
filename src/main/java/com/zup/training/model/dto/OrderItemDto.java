package com.zup.training.model.dto;

import javax.validation.constraints.Min;

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
public class OrderItemDto {
	
	@Min(value = 0L, message = "Id do produto não pode ser negativo.")
	private Long productId;
	
	@Min(value = 1, message = "Quantidade não pode ser menor ou igual a zero.")
	private Integer quantity;

}
