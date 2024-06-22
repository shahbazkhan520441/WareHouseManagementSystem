package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Stock;
import com.jsp.wms.requestdto.StockRequest;
import com.jsp.wms.responsedto.StockResponse;
@Component
public class StockMapper {
	@Autowired
	StorageMapper storageMapper;
//public Stock mapToStock(StockRequest stockRequest,Stock stock) {
//	stock.setStockQuantity(stockRequest.getStockQuantity());
//	stock.setStorage(stockRequest.getStorage());
//	stock.setInventory(stockRequest.getInventory());
//	return stock;
//}

public StockResponse mapToStockResponse(Stock stock) {
return	StockResponse.builder().stockId(stock.getStockId())
	                       .stockQuantity(stock.getStockQuantity())
	                       .storage(storageMapper.mapToStorageResponse(stock.getStorage()))
	                       .build();
	
	                       
}
}
