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


    @Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateInventory(InventoryRequest inventoryRequest,
			Integer storageId, Integer inventoryId) {

		return inventoryRepository.findById(inventoryId).map(inventory -> {

			Storage storage = storageRepository.findById(storageId)
					.orElseThrow(() -> new StorageNotFoundByIdException("Storage not Found"));

			List<Stock> stocks = stockRepository.findByInventoryAndStorage(inventory, storage);

//			if(inventory.getLengthInMeters() != inventoryRequest.getLengthInMeters()) 
			int totalQuantity = 0;
			for(Stock stock : stocks) {
				totalQuantity += stock.getStockQuantity();
			}

			double oldWeight = inventory.getWeightInKg();
			double oldLength = inventory.getLengthInMeters();
			double oldBreadth = inventory.getBreadthInMeters();
			double oldHeight = inventory.getHeightInMeters();

			double originalWeight = oldWeight * totalQuantity;
			double originalArea = oldBreadth * oldHeight + oldLength;

			inventoryMapper.mapInventoryRequestToInventory(inventoryRequest, inventory);

//			 if(oldQuantity != stock.getQuantity())
//			   inventory.setRestockedAt(LocalDate.now());

			double newWeight = inventory.getWeightInKg() * totalQuantity;
			double newArea = inventory.getBreadthInMeters() * inventory.getHeightInMeters()
					* inventory.getLengthInMeters();

			if (oldLength != inventory.getLengthInMeters() || oldBreadth != inventory.getBreadthInMeters()
					|| oldHeight != inventory.getHeightInMeters() || oldWeight != inventory.getWeightInKg()) {
				if (storage.getAvailabelArea() > 0 && storage.getMaxAdditionalWeight() > 0) {
					storage.setMaxAdditionalWeight(storage.getMaxAdditionalWeight() + (originalWeight - newWeight));
					storage.setAvailabelArea(storage.getAvailabelArea() + (originalArea - newArea));
				}
                 else
					throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");

			}

			inventory = inventoryRepository.save(inventory);
			storageRepository.save(storage);

		return	 ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<InventoryResponse>()
							.setData(inventoryMapper.mapInventoryToInventoryResponse(inventory))
							.setMessage("Inventory updated")
							.setStatus(HttpStatus.OK.value()));

			}).orElseThrow(() -> new InventoryNotExistException("Inventory not found"));
	}

    
    //--------------------------------------------------------------------------------------------------------------------


    //--------------------------------------------------------------------------------------------------------------------


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
	public ResponseEntity<ResponseStructure<StockResponse>> updateStock(Integer storageId,Integer inventoryId, Integer stockQuantity) {

		 Storage storage = storageRepository.findById(storageId)
				.orElseThrow(() -> new StorageNotFoundByIdException("Storage not Found"));
		Inventory inventory = inventoryRepository.findById(inventoryId)
				.orElseThrow(() -> new InventoryNotExistException("Inventory not Found"));

		Stock stock = new Stock();
		stock.setStockQuantity(stockQuantity);
		stock.setInventory(inventory);
		stock.setStorage(storage);
		stockRepository.save(stock);

		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<StockResponse>().setData(stockMapper.mapToStockResponse(stock))
						.setStatus(HttpStatus.FOUND.value()).setMessage("Inventories Found"));
	}



}
