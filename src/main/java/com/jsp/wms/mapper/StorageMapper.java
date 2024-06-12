package com.jsp.wms.mapper;

import com.jsp.wms.entity.Storage;
import com.jsp.wms.requestdto.StorageRequest;
import com.jsp.wms.responsedto.StorageResponse;

import jakarta.persistence.StoredProcedureParameter;

public class StorageMapper {
	public Storage mapToStorage(StorageRequest storageRequest,Storage storage) {
	     storage.setBlockName(storageRequest.getBlockName());
	     storage.setSection(storageRequest.getSection());
	    storage.setCapacityInArea(storageRequest.getLengthInMeter()*storageRequest.getBreadthInMeter()*storageRequest.getHeightInMeter());
	    storage.setCapacityInWeight(storageRequest.getCapacityInKg());
	     storage.setMaterialTypes(storageRequest.getMaterialTypes());
	     return storage;
	}
	
	public StorageResponse mapToStorageResponse(Storage storage) {
		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.blockName(storage.getBlockName())
				.capacityInArea(storage.getAvailabelArea())
				.capacityInWeight(storage.getCapacityInWeight())
				.materialTypes(storage.getMaterialTypes())
				.maxAdditionalWeight(storage.getMaxAdditionalWeight())
				.section(storage.getSection())
				.availabelArea(storage.getAvailabelArea())
				.build();
				
	}

}
