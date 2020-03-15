package com.zup.training.model.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class OrderDto {
	
	@NotBlank(message = "Campo 'nome do cliente' não pode estar vazio")
	private String clientName;
	
	@NotNull(message = "Campo 'Telefone' não pode estar em branco.")
	@Min(value = 0, message = "Campo 'Telefone' não pode ser nagativo")
	private Long clientPhone;
	
	@NotNull(message = "Campo 'itens' nao pode ser nulo")
	private List<OrderItemDto> items;
	
	@NotNull(message = "Campo 'desconto' nao pode ser nulo")
	@Min(value = 0, message = "Campo 'desconto' não pode ser menor que zero. Escolher entre 0 e 100")
	@Max(value = 100, message = "Campo 'desconto' não pode ser maior que 100. Escolher entre 0 e 100")
	private Integer discount;

}
