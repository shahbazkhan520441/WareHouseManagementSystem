package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;

import jakarta.persistence.StoredProcedureParameter;
@Component
public class StorageMapper {
	
	public Storage mapToStorage(StorageRequest storageRequest,Storage storage) {
	     storage.setBlockName(storageRequest.getBlockName());
	     storage.setSection(storageRequest.getSection());
	     storage.setMaterialTypes(storageRequest.getMaterialTypes());
		
	     return storage;
	     
	}
	
	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.materialTypes(storage.getMaterialTypes())
				.section(storage.getSection())
				.availabelArea(storage.getAvailabelArea())
				.build();
				
	}

}
