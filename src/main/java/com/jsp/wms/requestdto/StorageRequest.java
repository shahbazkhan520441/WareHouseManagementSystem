package com.jsp.wms.requestdto;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageRequest {
	private  String blockName;
	private String section;
	private List<MaterialTypes> materialTypes;
}
