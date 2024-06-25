package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
