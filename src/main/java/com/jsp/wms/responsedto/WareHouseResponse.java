package com.jsp.wms.responsedto;

import java.util.List;

import com.jsp.wms.entity.Address;
import com.jsp.wms.enums.AdminType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WareHouseResponse {
	private Integer wareHouseId;
	private String wareHouseName;
	private double totalCapacity;
	
	private AddressResponse address;
}
