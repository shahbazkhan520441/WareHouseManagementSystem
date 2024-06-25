package com.jsp.wms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Address;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;

@Component
public class AddressMapper {
	
	public Address mapToAddress(AddressRequest addressRequest,Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setLongitude(addressRequest.getLongitude());
		address.setLatitude(addressRequest.getLatitude());
		return address;
	}
	
	public AddressResponse mapToAddressResponse(Address address ) {
	return	AddressResponse.builder()
     	 .addressId(address.getAddressId())
		 .addressLine(address.getAddressLine())
		 .city(address.getCity())
		 .pincode(address.getPincode())
		 .state(address.getState())
		 .country(address.getCountry())
		 .longitude(address.getLongitude())
		 .latitude(address.getLatitude())
		 .build();
	}
	
	
	

}
