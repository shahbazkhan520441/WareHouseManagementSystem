package com.jsp.wms.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Client;
import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.Stock;
import com.jsp.wms.entity.Storage;
import com.jsp.wms.mapper.InventoryMapper;
import com.jsp.wms.mapper.StockMapper;
import com.jsp.wms.repository.ClientRepository;
import com.jsp.wms.repository.InventoryRepository;
import com.jsp.wms.repository.StockRepository;
import com.jsp.wms.repository.StorageRepository;
import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.service.InventoryService;
import com.jsp.wms.util.ResponseStructure;

import jakarta.validation.Valid;

import com.jsp.wms.exception.ClientNotExistException;
import com.jsp.wms.exception.InventoryNotExistException;
import com.jsp.wms.exception.StorageTypeNotExistException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private  StockMapper stockMapper;
    @Autowired
    private StockRepository stockRepository;
    //--------------------------------------------------------------------------------------------------------------------

    @Override
    public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(
            InventoryRequest inventoryRequest, Integer storageId, Integer clientId) {
         Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotExistException("ClientId : " + clientId + ", is not exist"));

        return   storageRepository.findById(storageId).map(storage -> {
            Inventory inventory = inventoryMapper.mapInventoryRequestToInventory(inventoryRequest, new Inventory());
            inventory.setRestockedAt(LocalDate.now());

            double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
            double updatedStorageArea = storage.getAvailabelArea() - (productSize * inventory.getQuantity());
            storage.setAvailabelArea(updatedStorageArea);

            double updatedStorageMaxWeight = storage.getMaxAdditionalWeight() - (inventory.getWeightInKg() * inventory.getQuantity());
            storage.setMaxAdditionalWeight(updatedStorageMaxWeight);

            inventory.setClient(client);
            inventory = inventoryRepository.save(inventory);
            
            storage = storageRepository.save(storage);
            
//            3 line
            Stock stock=new Stock();
            stock.setStockQuantity(inventoryRequest.getQuantity());
            stock.setInventory(inventory);
            stock.setStorage(storage);
            stock = stockRepository.save(stock);
            
            inventory.setStocks(List.of(stock));
            storage.setStocks(List.of(stock));
           
        return     ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Inventory Created")
                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
        }).orElseThrow(() -> new StorageTypeNotExistException("StorageId : " + storageId + ", is not exist"));
    }

    //--------------------------------------------------------------------------------------------------------------------
//    @Override
//    public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest, Long inventoryId) {
//        return inventoryRepository.findById(inventoryId).map(inventory -> {
//            inventory = inventoryMapper.mapInventoryRequestToInventory(inventoryRequest, inventory);
//
////            List<Storage> listStorages = getUpdatedStorages(inventory);
////            inventory.setStorages(listStorages);
//            inventory = inventoryRepository.save(inventory);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<InventoryResponse>()
//                    .setStatus(HttpStatus.CREATED.value())
//                    .setMessage("Inventory Updated")
//                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
//        }).orElseThrow(() -> new InventoryNotExistException("InventoryId : " + inventoryId + ", is not exist"));
//    }
//
//    private static List<Storage> getUpdatedStorages(Inventory inventory) {
//        double productSize = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
//        double qnt = inventory.getQuantity();
//
//        double maxWeight = inventory.getWeightInKg() * inventory.getQuantity();
//
//////        List<Storage> listStorages = inventory.getStorages();
////        listStorages.forEach(storage -> {
////            double updatedStorageArea = storage.getAvailabelArea() - (productSize * qnt);
////            storage.setAvailabelArea(updatedStorageArea);
////
////            double updatedStorageMaxWeight = storage.getMaxAdditionalWeight() - maxWeight;
////            storage.setMaxAdditionalWeight(updatedStorageMaxWeight);
////        });
////        return listStorages;
//    }

    //--------------------------------------------------------------------------------------------------------------------
    @Override
    public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(Long inventoryId) {
        return inventoryRepository.findById(inventoryId).map(inventory -> {
            return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<InventoryResponse>()
                    .setStatus(HttpStatus.FOUND.value())
                    .setMessage("Inventory Founded")
                    .setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
        }).orElseThrow(() -> new InventoryNotExistException("InventoryId : " + inventoryId + ", is not exist"));
    }

    //--------------------------------------------------------------------------------------------------------------------
    @Override
    public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories() {
        List<InventoryResponse> inventoryResponses = inventoryRepository
                .findAll()
                .stream()
                .map(inventory -> inventoryMapper.mapInventoryToInventoryResponse(inventory))
                .toList();
        return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
                .setStatus(HttpStatus.FOUND.value())
                .setMessage("Inventories are Founded")
                .setData(inventoryResponses));
    }
    //--------------------------------------------------------------------------------------------------------------------

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(
			@Valid InventoryRequest inventoryRequest, @Valid Long inventoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}
