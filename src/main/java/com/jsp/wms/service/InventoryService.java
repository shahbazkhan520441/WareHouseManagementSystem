package com.jsp.wms.service;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;

public interface InventoryService {
    ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(
            @Valid InventoryRequest inventoryRequest, @Valid Integer storageId, @Valid Integer clientId);

    ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
            @Valid InventoryRequest inventoryRequest, @Valid Long inventoryId);

    ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(@Valid Long inventoryId);

    ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories();
}
