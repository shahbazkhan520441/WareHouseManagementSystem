package com.jsp.wms.responsedto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageTypeResponse {

    private Integer storageTypeId;
    private double lengthInMeters;
    private double breadthInMeters;
    private double heightInMeters;
    private double capacityWeightInKg;
    private double  unitsAvailable;

}
