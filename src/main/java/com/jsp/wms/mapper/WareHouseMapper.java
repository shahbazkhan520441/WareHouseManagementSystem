package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.WareHouseResponse;
@Component
public class WareHouseMapper {
	@Autowired
	private  AddressMapper addressMapper;
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest,WareHouse wareHouse) {
		wareHouse.setWareHouseName(wareHouseRequest.getWareHouseName());
		 
		return wareHouse;
	}
	
	public WareHouseResponse mapTowareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
			.wareHouseId(wareHouse.getWareHouseId())
			.wareHouseName(wareHouse.getWareHouseName()
					).totalCapacity(wareHouse.getTotalCapcity())
				.build();
	}

public WareHouseResponse mapToWareHouseAddress(Address address,WareHouse  wareHouse) {
	return WareHouseResponse.builder().wareHouseId(wareHouse.getWareHouseId())
			.wareHouseName(wareHouse.getWareHouseName())
			.address(addressMapper.mapToAddressResponse(address)).build();
			                               
			                                 
}
}
