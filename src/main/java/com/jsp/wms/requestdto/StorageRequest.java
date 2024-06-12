package com.jsp.wms.requestdto;

import com.jsp.wms.enums.MaterialTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageRequest {

	private  String blockName;
	private String section;
	private Integer lengthInMeter;
	private Integer breadthInMeter;
	private Integer heightInMeter;
	private Integer capacityInKg;
	private MaterialTypes materialTypes;
}
