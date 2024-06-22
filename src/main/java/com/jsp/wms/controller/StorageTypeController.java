package com.jsp.wms.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.wms.requestdto.StorageTypeRequest;
import com.jsp.wms.responsedto.StorageTypeResponse;
import com.jsp.wms.service.StorageTypeService;
import com.jsp.wms.util.ResponseStructure;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StorageTypeController {

    @Autowired
    private StorageTypeService storageTypeService;
    //--------------------------------------------------------------------------------------------------------------------

    @PostMapping("/storageTypes")
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType(
            @RequestBody StorageTypeRequest storageTypeRequest){

      return storageTypeService.addStorageType(storageTypeRequest);
    }
    //--------------------------------------------------------------------------------------------------------------------
    @PutMapping("/storageTypes/{storageTypeId}")
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
            @RequestBody StorageTypeRequest storageTypeRequest,
            @PathVariable Integer storageTypeId){
        return storageTypeService.updateStorageType(storageTypeRequest, storageTypeId);
    }
    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/storageTypes/{storageTypeId}")
    public ResponseEntity<ResponseStructure<StorageTypeResponse>> findStorageType(@PathVariable Integer storageTypeId){
        return storageTypeService.findStorageType(storageTypeId);
    }
    //--------------------------------------------------------------------------------------------------------------------
    @GetMapping("/storageTypes")
    public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findStorageTypes(){
        return storageTypeService.findStorageTypes();
    }

    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------


}