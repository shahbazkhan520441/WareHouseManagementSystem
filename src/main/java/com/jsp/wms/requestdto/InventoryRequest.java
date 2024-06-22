package com.jsp.wms.requestdto;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.jsp.wms.enums.MaterialTypes;

@Getter
@Setter
public class InventoryRequest {
    private String productTitle;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double weightInKg;
    private int quantity;
    private List<MaterialTypes> materialTypes;
    private Long sellerId;
}