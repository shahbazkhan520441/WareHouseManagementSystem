package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("api/v1")
@PreAuthorize("hasAuthority('')")
public class StorageController {
	@Autowired
	StorageService storageService;
	@PostMapping("/warehouses/{wareHouseId}/storages")
	public ResponseEntity<ResponseStructure<StorageResponse>> createStorage(@RequestBody StorageRequest  storageRequest,@PathVariable Integer wareHouseId){
		return storageService.createStorage(storageRequest,wareHouseId);
	}
	

}
