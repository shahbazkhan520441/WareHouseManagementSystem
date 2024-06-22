package com.jsp.wms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.wms.entity.PurchaseOrders;

public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrders, Integer> {

}
