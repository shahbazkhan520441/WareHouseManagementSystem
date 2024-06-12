package com.jsp.wms.entity;

import com.jsp.wms.enums.MaterialTypes;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private Integer capacityInArea;
	private Integer capacityInWeight;
	private Integer maxAdditionalWeight;
	@Enumerated(EnumType.STRING)
	private MaterialTypes materialTypes;
	private Integer availabelArea;
	@ManyToOne
	private WareHouse wareHouse;

}
