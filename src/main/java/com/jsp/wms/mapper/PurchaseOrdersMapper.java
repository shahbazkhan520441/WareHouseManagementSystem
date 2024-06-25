package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.PurchaseOrders;
import com.jsp.wms.requestdto.PurchaseOrdersRequest;
import com.jsp.wms.responsedto.PurchaseOrdersResponse;
@Component
public class PurchaseOrdersMapper {

	public PurchaseOrders mapToPurchaseOrders( PurchaseOrdersRequest  purchaseOrdersRequest,PurchaseOrders purchaseOrders){
		purchaseOrders.setOrderQuantity(purchaseOrdersRequest.getOrderQuantity());
		purchaseOrders.setInvoiceLink(purchaseOrdersRequest.getInvoiceLink());
		purchaseOrders.setCustomerId(purchaseOrdersRequest.getCustomerId());
		
		return purchaseOrders;
	}
	
	public PurchaseOrdersResponse mapToPurchaseOrdersResponse(PurchaseOrders purchaseOrders) {
		return PurchaseOrdersResponse.builder()
		                 .purchaseOrderId(purchaseOrders.getPurchaseOrderId())
		                 .orderQuantity(purchaseOrders.getOrderQuantity())
		                 .invoiceLink(purchaseOrders.getInvoiceLink())
		                 .customerId(purchaseOrders.getCustomerId())
		                 .build();
		
	}
	
}
