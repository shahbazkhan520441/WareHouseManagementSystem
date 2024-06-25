package com.jsp.wms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer stockId;
	private Integer stockQuantity;
	@ManyToOne
	private Inventory inventory;
	@ManyToOne
	private Storage storage;

}
