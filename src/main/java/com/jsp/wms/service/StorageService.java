package com.jsp.wms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.util.ResponseStructure;
import com.jsp.wms.util.SimpleStructure;

public interface StorageService {

	   public ResponseEntity<ResponseStructure<String>> createStorage(
	            StorageRequest storageRequest,
	            Integer wareHouseId,
	            Integer storageTypeId,
	            Integer noOfStorageUnits);
	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest, Integer storageId);
	public ResponseEntity<ResponseStructure<StorageResponse>> findStorage(Integer storageId);
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> findAllStorages();
}
