package com.jsp.wms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class WareHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer wareHouseId;
	private String wareHouseName;
	
	@OneToOne
	private Admin admin;

}
