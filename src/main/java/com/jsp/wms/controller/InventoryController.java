package com.jsp.wms.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
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
    @PutMapping("/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
            @Valid @RequestBody InventoryRequest inventoryRequest,
            @Valid @PathVariable Long inventoryId) {
        return inventoryService.updateInventory(inventoryRequest, inventoryId);
    }

    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/inventories/{inventoryId}")
    public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(
            @Valid @PathVariable Long inventoryId) {
        return inventoryService.findInventory(inventoryId);
    }
    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/inventories")
    public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories(){
        return inventoryService.findInventories();
    }

    //--------------------------------------------------------------------------------------------------------------------

}