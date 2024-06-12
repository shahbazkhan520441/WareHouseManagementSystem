package com.jsp.wms.responsedto;

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
	private Integer capacityInArea;
	private Integer capacityInWeight;
	private Integer maxAdditionalWeight;
	private MaterialTypes materialTypes;
	private Integer availabelArea;
}
