package com.jsp.wms.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.responsedto.StockResponse;
import com.jsp.wms.service.InventoryService;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    //--------------------------------------------------------------------------------------------------------------------

    @PostMapping("/storages/{storageId}/clients/{clientId}/inventories")
    public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(
            @Valid @RequestBody InventoryRequest inventoryRequest,
         @Valid @PathVariable   Integer storageId,@Valid @PathVariable  Integer clientId) {
        return inventoryService.addInventory(inventoryRequest, storageId, clientId);
    }

    //--------------------------------------------------------------------------------------------------------------------

    @PutMapping("/storages/{storageId}/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			Integer storageId, Integer inventoryId){
    	return inventoryService.updateInventory(inventoryRequest, inventoryId,storageId);
    }
    @PutMapping("/storages/{storageId}/stocks/stockQuantity/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<StockResponse>> updateStock(Integer storageId, Integer inventoryId, Integer stockQuantity){
    	return inventoryService.updateStock(storageId,inventoryId,storageId);
    }
    
 
    @PutMapping("/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
            @Valid @RequestBody InventoryRequest inventoryRequest,
            @Valid @PathVariable Integer inventoryId) {
        return inventoryService.updateInventory(inventoryRequest, inventoryId);
    }


    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory( updateinventory
            @Valid @PathVariable Integer inventoryId) {
            

        return inventoryService.findInventory(inventoryId);
    }
    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/inventories")
    public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories(){
        return inventoryService.findInventories();
    }

    //--------------------------------------------------------------------------------------------------------------------

}