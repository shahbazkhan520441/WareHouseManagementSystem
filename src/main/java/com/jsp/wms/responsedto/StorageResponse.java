package com.jsp.wms.responsedto;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageResponse {
	private Integer storageId;
	private  String blockName;
	private String section;
	private double capacityInWeight;
	private double maxAdditionalWeight;
	private List<MaterialTypes> materialTypes;
	private double availabelArea;
}
