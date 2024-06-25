package com.jsp.wms.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrdersRequest {
	private Integer orderQuantity;
	private String invoiceLink;
	private Integer customerId;
}
