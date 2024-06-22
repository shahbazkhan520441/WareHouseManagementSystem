package com.jsp.wms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.PurchaseOrders;
import com.jsp.wms.mapper.PurcahseOrdersMapper;
import com.jsp.wms.repository.PurchaseOrdersRepository;
import com.jsp.wms.requestdto.PurcahseOrdersRequest;
import com.jsp.wms.responsedto.PurcahseOrdersResponse;
import com.jsp.wms.service.PurcahseOrdersService;
import com.jsp.wms.util.ResponseStructure;
@Service
public class PurcahseOrdersServiceImpl implements PurcahseOrdersService {
    @Autowired
	PurchaseOrdersRepository purchaseOrdersRepository;
	@Autowired
	PurcahseOrdersMapper purcahseOrdersMapper;
	@Override
	public ResponseEntity<ResponseStructure<PurcahseOrdersResponse>> createPurchaseOrder(
			PurcahseOrdersRequest purcahseOrdersRequest) {
	PurchaseOrders purchaseOrders= purcahseOrdersMapper.mapToPurchaseOrders(purcahseOrdersRequest, new PurchaseOrders());
	purchaseOrders=  purchaseOrdersRepository.save(purchaseOrders);
  return	ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<PurcahseOrdersResponse>()
			                                         .setStatus(HttpStatus.CREATED.value())
			                                         .setMessage("purcahse order creared sucessfully")
			                                         .setData(purcahseOrdersMapper.mapToPurchaseOrdersResponse(purchaseOrders)));
	}

}
