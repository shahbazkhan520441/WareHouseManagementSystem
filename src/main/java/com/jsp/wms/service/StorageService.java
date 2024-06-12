package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.util.ResponseStructure;

public interface StorageService {

	ResponseEntity<ResponseStructure<StorageResponse>> createStorage(StorageRequest storageRequest, Integer wareHouseId);

}
