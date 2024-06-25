package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.PurcahseOrdersRequest;
import com.jsp.wms.responsedto.PurcahseOrdersResponse;
import com.jsp.wms.service.PurcahseOrdersService;
import com.jsp.wms.util.ResponseStructure;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class PurcahseOrdersController {
   
	 @Autowired
	PurcahseOrdersService purcahseOrdersService;
	@PutMapping("/PurcahseOrders")
    public ResponseEntity<ResponseStructure<PurcahseOrdersResponse>> createPurchaseOrder(@RequestBody PurcahseOrdersRequest  purcahseOrdersRequest){
    	return purcahseOrdersService.createPurchaseOrder(purcahseOrdersRequest);
    }
    
}
