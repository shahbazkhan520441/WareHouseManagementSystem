package com.jsp.wms.responsedto;

import java.time.LocalDate;
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
public class PurchaseOrdersResponse {
	private Integer purchaseOrderId;
	private Integer orderQuantity;
	private String invoiceLink;
	private Integer customerId;
}
