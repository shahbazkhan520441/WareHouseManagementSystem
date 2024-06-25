package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.PurcahseOrdersRequest;
import com.jsp.wms.responsedto.PurcahseOrdersResponse;
import com.jsp.wms.util.ResponseStructure;

public interface PurcahseOrdersService {

	ResponseEntity<ResponseStructure<PurcahseOrdersResponse>> createPurchaseOrder(
			PurcahseOrdersRequest purcahseOrdersRequest);
       
}
