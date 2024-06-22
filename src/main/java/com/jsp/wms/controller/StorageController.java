package com.jsp.wms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;
import com.jsp.wms.service.StorageService;
import com.jsp.wms.util.ResponseStructure;
import com.jsp.wms.util.SimpleStructure;

@RestController
@RequestMapping("api/v1")

public class StorageController {
	@Autowired
	StorageService storageService;
	@PreAuthorize("hasAuthority('CREATE_STORAGE')")
	@PostMapping("/warehouses/{wareHouseId}/{storageTypeId}/storages")
	public ResponseEntity<ResponseStructure<String>> createStorage(@RequestBody StorageRequest  storageRequest,@PathVariable Integer wareHouseId,@PathVariable Integer storageTypeId, @RequestParam("no_of_storage_units") Integer noOfStorageUnits ){
		return storageService.createStorage(storageRequest,wareHouseId,storageTypeId, noOfStorageUnits);
	}
	@PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	@PutMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(@RequestBody StorageRequest storageRequest,@PathVariable Integer storageId){
		return storageService.updateStorage(storageRequest,storageId);
	}
	
	@GetMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> findStorage(Integer storageId){
		return storageService.findStorage(storageId);
	}
	@GetMapping("/storages")
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> findAllStorages(){
		return storageService.findAllStorages();	}
	

}
