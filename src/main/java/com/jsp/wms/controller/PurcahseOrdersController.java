package com.jsp.wms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.PurchaseOrdersRequest;
import com.jsp.wms.responsedto.PurchaseOrdersResponse;
import com.jsp.wms.service.PurcahseOrdersService;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PurcahseOrdersController {
   
    @Autowired
	PurcahseOrdersService purcahseOrdersService;
	  
	    //--------------------------------------------------------------------------------------------------------------------

	   
	    @PostMapping("/inventories/{inventoryId}/purchaseOrders")
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> addPurchaseOrder(
	            @Valid @RequestBody PurchaseOrdersRequest purchaseOrderRequest,
	            @Valid @PathVariable Integer inventoryId) {
	        return purcahseOrdersService.addPurchaseOrder(purchaseOrderRequest, inventoryId);
	    }
	    //--------------------------------------------------------------------------------------------------------------------

//	    note : this method is only for demo purpose
	
	        
	    @PutMapping("/purchaseOrders/{orderId}")
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> updatePurchaseOrder(
	            @Valid @RequestBody PurchaseOrdersRequest purchaseOrderRequest,
	            @Valid @PathVariable Integer purchaseOrderId) {
	        return purcahseOrdersService.updatePurchaseOrder(purchaseOrderRequest, purchaseOrderId);
	    }
	    //--------------------------------------------------------------------------------------------------------------------

	    @GetMapping("/purchaseOrders/{orderId}")
	    public ResponseEntity<ResponseStructure<PurchaseOrdersResponse>> findPurchaseOrder(@Valid @PathVariable Integer purchaseOrderId){
	        return purcahseOrdersService.findPurchaseOrder(purchaseOrderId);
	    }
	    //--------------------------------------------------------------------------------------------------------------------

	 
	         
	    @GetMapping("/purchaseOrders")
	    public ResponseEntity<ResponseStructure<List<PurchaseOrdersResponse>>> findPurchaseOrders(){
	        return purcahseOrdersService.findPurchaseOrders();
	    }
	    //--------------------------------------------------------------------------------------------------------------------

	}
    

