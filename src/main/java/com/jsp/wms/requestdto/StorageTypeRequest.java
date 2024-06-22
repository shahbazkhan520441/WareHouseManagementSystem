package com.jsp.wms.requestdto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageTypeRequest {

    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double capacityWeightInKg;

}
