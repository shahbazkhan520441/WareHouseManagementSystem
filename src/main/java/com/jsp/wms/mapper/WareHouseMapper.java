package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.WareHouseResponse;
@Component
public class WareHouseMapper {
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest,WareHouse wareHouse) {
		wareHouse.setWareHouseName(wareHouseRequest.getWareHouseName());
		 
		return wareHouse;
	}
	
	public WareHouseResponse mapTowareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
			.wareHouseId(wareHouse.getWareHouseId())
			.wareHouseName(wareHouse.getWareHouseName()
					).totalCapacity(0)
				.build();
	}

}
