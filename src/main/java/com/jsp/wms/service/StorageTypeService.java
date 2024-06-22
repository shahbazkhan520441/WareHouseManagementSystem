package com.jsp.wms.service;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;
import java.util.Map;

public interface StorageTypeService {
    ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType(
            @Valid StorageTypeRequest storageTypeRequest);

    ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(
            @Valid Integer storageTypeId);

    ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes();

    ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
            @Valid StorageTypeRequest storageTypeRequest, @Valid Integer storageTypeId);

}