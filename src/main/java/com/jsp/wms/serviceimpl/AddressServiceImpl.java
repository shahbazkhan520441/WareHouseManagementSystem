package com.jsp.wms.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Address;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.AddresNotFoundByIdException;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AddressMapper;
import com.jsp.wms.repository.AddressRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.AddressRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.service.AddressService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	WareHouseRepository wareHouseRepository;
	@Autowired
	AddressMapper  addressMapper; 

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> createAddress(AddressRequest addressRequest,Integer wareHouseId) {
		return  wareHouseRepository.findById(wareHouseId).map(warehouse->{
			Address address=	addressMapper.mapToAddress(addressRequest, new Address());
					address.setWareHouse(warehouse);
		            address = addressRepository.save(address);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AddressResponse>()
			.setStatus(HttpStatus.CREATED.value())
			.setMessage("address created and saved to database")
			.setData(addressMapper.mapToAddressResponse(address)));
		 }).orElseThrow(()->new WarehouseNotFoundByIdException("invalid warehouse id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,Integer addressId) {
		return addressRepository.findById(addressId).map(exAddress->{
			Address address=addressMapper.mapToAddress(addressRequest,exAddress);
			exAddress=addressRepository.save(address);
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<AddressResponse>()
					                                   .setStatus(HttpStatus.OK.value())
					                                   .setMessage("address updated")
					                                   .setData(addressMapper.mapToAddressResponse(exAddress)));
		}).orElseThrow(()-> new AddresNotFoundByIdException("Not Found"));
	}

}
