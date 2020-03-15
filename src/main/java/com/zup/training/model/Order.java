package com.zup.training.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@NotBlank(message = "Campo 'Nome do cliente' não pode estar em branco")
	private String clientName;
	
	@NotNull(message = "Campo 'Telefone' não pode estar em branco.")
	@Min(value = 0, message = "Campo 'Telefone' não pode ser nagativo")
	private Long clientPhone;
	
	@NotNull(message = "Campo 'itens' nao pode ser nulo")
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "order")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<OrderItem> items;
	
	@NotNull(message = "Campo 'desconto' nao pode ser nulo")
	@Min(value = 0, message = "Campo 'desconto' não pode ser menor que zero. Escolher entre 0 e 100")
	@Max(value = 100, message = "Campo 'desconto' não pode ser maior que 100. Escolher entre 0 e 100")
	private Integer discount;
	
	private Double totalPrice;
	private Double orderFinalPrice;
		
}
