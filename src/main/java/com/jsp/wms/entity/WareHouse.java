package com.jsp.wms.entity;

import java.util.List;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private double totalCapacityInKg;
	private double totalCapcity;
	
	@OneToOne
	private Admin admin;
	@OneToMany(mappedBy = "wareHouse")
	private List<Storage> storages;

}
