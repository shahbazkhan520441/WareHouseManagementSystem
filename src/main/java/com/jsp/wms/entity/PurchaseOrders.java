package com.jsp.wms.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrders {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer purchaseOrderId;
	 private double orderQuantity;
	 private String invoiceLink;
	 private Integer CustomerId;
	 @ManyToMany
	 List<Inventory> inventory;

}
