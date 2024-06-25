package com.jsp.wms.responsedto;

import com.jsp.wms.entity.Storage;

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
public class StockResponse {
	private Integer stockId;
	private Integer stockQuantity;
	
	private  StorageResponse storage;
	 

}
