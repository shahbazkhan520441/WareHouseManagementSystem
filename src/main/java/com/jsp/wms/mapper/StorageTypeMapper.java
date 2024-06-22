package com.jsp.wms.mapper;


import org.springframework.stereotype.Component;

import com.jsp.wms.entity.StorageType;
import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;

@Component
public class StorageTypeMapper {

    public StorageType mapStorageTypeRequestToStorageType( StorageTypeRequest storageTypeRequest, StorageType storageType){
        storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
        storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
        storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
        storageType.setCapacityWeightInKg(storageTypeRequest.getCapacityWeightInKg());
       return storageType;
    }

    public StorageTypeResponse mapStorageTypeToStorageTypeResponse(StorageType storageType){
        return StorageTypeResponse.builder()
                .storageTypeId(storageType.getStorageTypeId())
                .lengthInMeters(storageType.getLengthInMeters())
                .breadthInMeters(storageType.getBreadthInMeters())
                .heightInMeters(storageType.getHeightInMeters())
                .capacityWeightInKg(storageType.getCapacityWeightInKg())
                .unitsAvailable(storageType.getUnitsAvailable())
                .build();
    }
}
