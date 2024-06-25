package com.jsp.wms.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurcahseOrdersRequest {
	private double orderQuantity;
	private String invoiceLink;
	private Integer CustomerId;
}
