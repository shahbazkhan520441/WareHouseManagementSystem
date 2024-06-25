package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.PurchaseOrdersRequest;
import com.jsp.wms.responsedto.PurchaseOrdersResponse;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface PurcahseOrdersService {

	ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> addPurchaseOrder(
			@Valid PurchaseOrdersRequest purchaseOrderRequest, @Valid Integer inventoryId);

	ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> updatePurchaseOrder(
			@Valid PurchaseOrdersRequest purchaseOrderRequest, @Valid Integer purchaseOrderId);

	ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> findPurchaseOrder(@Valid Integer purchaseOrderId);

	ResponseEntity<ResponseStructure<List<PurchaseOrdersResponse>>> findPurchaseOrders();



       
}
