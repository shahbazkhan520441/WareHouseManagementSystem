package com.jsp.wms.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    private String productTitle;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double weightInKg;
    private int quantity;
    private List<MaterialTypes> materialTypes;
    private LocalDate restockedAt;
    private Long sellerId;
  

    @ManyToOne
    private Client client;
    
    @ManyToMany(mappedBy = "inventory")
    List<PurchaseOrders> purchaseOrders;
    
    @OneToMany(mappedBy = "inventory")
    private List<Stock>  stocks;
    
}
