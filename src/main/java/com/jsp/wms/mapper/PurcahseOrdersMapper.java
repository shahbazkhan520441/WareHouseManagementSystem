package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.PurchaseOrders;
import com.jsp.wms.requestdto.PurcahseOrdersRequest;
import com.jsp.wms.responsedto.PurcahseOrdersResponse;
@Component
public class PurcahseOrdersMapper {

	public PurchaseOrders mapToPurchaseOrders( PurcahseOrdersRequest purcahseOrdersRequest,PurchaseOrders purchaseOrders){
		purchaseOrders.setOrderQuantity(purcahseOrdersRequest.getOrderQuantity());
		purchaseOrders.setInvoiceLink(purcahseOrdersRequest.getInvoiceLink());
		purchaseOrders.setCustomerId(purcahseOrdersRequest.getCustomerId());
		
		return purchaseOrders;
	}
	
	public PurcahseOrdersResponse mapToPurchaseOrdersResponse(PurchaseOrders purchaseOrders) {
		return PurcahseOrdersResponse.builder()
		                 .purchaseOrderId(purchaseOrders.getPurchaseOrderId())
		                 .orderQuantity(purchaseOrders.getOrderQuantity())
		                 .invoiceLink(purchaseOrders.getInvoiceLink())
		                 .CustomerId(purchaseOrders.getCustomerId())
		                 .build();
		
	}
	
}
