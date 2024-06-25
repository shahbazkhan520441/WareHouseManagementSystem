package com.jsp.wms.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Inventory;
import com.jsp.wms.entity.Stock;
import com.jsp.wms.entity.Storage;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	List<Stock> findByInventoryAndStorage(Inventory inventory, Storage storage);


}
