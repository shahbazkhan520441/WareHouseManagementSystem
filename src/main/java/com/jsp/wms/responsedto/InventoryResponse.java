package com.jsp.wms.responsedto;


import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.jsp.wms.entity.Stock;
import com.jsp.wms.enums.MaterialTypes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    private Integer inventoryId;

    private String productTitle;
    private Double lengthInMeters;
    private Double breadthInMeters;
    private Double heightInMeters;
    private Double weightInKg;
    private Integer quantity;
    private List<MaterialTypes> materialTypes;
    private LocalDate restockedAt;
    private Long sellerId;
    
    
   private List<StockResponse>  stocks;
    
}
