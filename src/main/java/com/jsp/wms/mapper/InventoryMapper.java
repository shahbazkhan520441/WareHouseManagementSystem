package com.jsp.wms.mapper;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Inventory;
import com.jsp.wms.requestdto.InventoryRequest;
import com.jsp.wms.responsedto.InventoryResponse;
import com.jsp.wms.responsedto.StockResponse;

@Component
public class InventoryMapper {
	@Autowired
	private StockMapper stockMapper;
    public Inventory mapInventoryRequestToInventory(InventoryRequest inventoryRequest, Inventory inventory) {
        inventory.setProductTitle(inventoryRequest.getProductTitle());
        inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
        inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
        inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
        inventory.setWeightInKg(inventoryRequest.getWeightInKg());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
        inventory.setSellerId(inventoryRequest.getSellerId());
        return inventory;
    }

    public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
    	List<StockResponse> listStocks = inventory.getStocks().stream().map(stock-> stockMapper.mapToStockResponse(stock)).toList();
        return InventoryResponse.builder()
                .inventoryId(inventory.getInventoryId())
                .productTitle(inventory.getProductTitle())
                .lengthInMeters(inventory.getLengthInMeters())
                .breadthInMeters(inventory.getBreadthInMeters())
                .heightInMeters(inventory.getHeightInMeters())
                .weightInKg(inventory.getWeightInKg())
                .quantity(inventory.getQuantity())
                .materialTypes(inventory.getMaterialTypes())
                .restockedAt(inventory.getRestockedAt())
                .sellerId(inventory.getSellerId())
                .stocks(listStocks)
                .build();
    }
}