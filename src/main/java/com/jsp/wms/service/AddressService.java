package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.util.ResponseStructure;

public interface AddressService {

public 	ResponseEntity<ResponseStructure<AddressResponse>> createAddress(AddressRequest addressRequest,Integer wareHouseId);

public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, Integer addressId);

}
