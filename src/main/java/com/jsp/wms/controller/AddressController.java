package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("api/v1")
public class AddressController {
	@Autowired
	AddressService addressService;
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	@PostMapping("warehouse/{wareHouseId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(@RequestBody AddressRequest addressRequest,@PathVariable Integer wareHouseId){
		return addressService.createAddress(addressRequest,wareHouseId);
	}
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
    @PutMapping("address/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@RequestBody AddressRequest addressRequest ,@PathVariable Integer  addressId){
		return addressService.updateAddress(addressRequest, addressId);
		
	}

}
