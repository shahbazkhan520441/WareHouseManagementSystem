package com.jsp.wms.service;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.responsedto.StockResponse;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;

public interface InventoryService {
    ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(
            @Valid InventoryRequest inventoryRequest, @Valid Integer storageId, @Valid Integer clientId);

  
    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			Integer storageId, Integer inventoryId);
    public ResponseEntity<ResponseStructure<StockResponse>> updateStock(Integer storageId, Integer inventoryId, Integer stockQuantity);

    ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(@Valid Integer inventoryId);

    ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories();
}
