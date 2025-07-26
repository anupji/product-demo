package com.soft.app.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductApiRequest {

	private Long id;

	private String name;

	private Integer quantity;

	private Double price;
	

}
