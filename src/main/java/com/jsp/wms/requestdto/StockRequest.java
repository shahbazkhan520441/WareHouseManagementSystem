package com.jsp.wms.requestdto;

import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.Storage;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequest {
	private Integer stockQuantity;
	private Inventory inventory;
	private Storage storage;
}
