package com.jsp.wms.entity;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Storage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer storageId;
	private  String blockName;
	private String section;
	private double maxAdditionalWeight;
	@Enumerated(EnumType.STRING)
	private List<MaterialTypes> materialTypes;
	private double availabelArea;
	private Integer sellerId;
	@ManyToOne
	private WareHouse wareHouse;
	@ManyToOne
	private StorageType storageType;
	@OneToMany
	private List<Stock> stocks;
	
	
}
