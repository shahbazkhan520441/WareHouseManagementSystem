package com.jsp.wms.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Address;
import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.WarehouseNotFoundByCityException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AddressMapper;
import com.jsp.wms.mapper.WareHouseMapper;
import com.jsp.wms.repository.AddressRepository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AddressResponse;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;

@Service
public class WareHouseImpl implements WareHouseService {
	@Autowired
	WareHouseRepository wareHouseRepository;
	@Autowired
	WareHouseMapper wareHouseMapper;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	AddressMapper addressMapper;

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// Collection<? extends GrantedAuthority> authorities =
		// authentication.getAuthorities();
		// for (GrantedAuthority grantedAuthority : authorities) {
		// if(grantedAuthority.getAuthority().equals("CREATE_WAREHOUSE")) {
		// WareHouse wareHouse=wareHouseMapper.mapToWareHouse(wareHouseRequest, new
		// WareHouse());
		// wareHouse=wareHouseRepository.save(wareHouse);
		// return ResponseEntity.status(HttpStatus.CREATED).body(new
		// ResponseStructure<WareHouseResponse>(
		// ).setStatus(HttpStatus.CREATED.value())
		// .setMessage("warehouse created")
		// .setData(wareHouseMapper.mapTowareHouseResponse(wareHouse)));
		// }
		//
		// }
		// return null; ISTED OF ABOVE LOGIC FOR SECURITY BEFORE ACCES WE USE
		// @PreAuthorize annoation

		WareHouse wareHouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
		wareHouse.setTotalCapcity(0);
		wareHouse.setTotalCapacityInKg(0);
		wareHouse = wareHouseRepository.save(wareHouse);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WareHouseResponse>().setStatus(HttpStatus.CREATED.value())
						.setMessage("warehouse created").setData(wareHouseMapper.mapTowareHouseResponse(wareHouse)));

	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,
			int wareHouseId) {
		return wareHouseRepository.findById(wareHouseId).map(exwareHouse -> {
			WareHouse warehouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, exwareHouse);
			exwareHouse = wareHouseRepository.save(warehouse);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>().setStatus(HttpStatus.OK.value())
							.setMessage("warehouse updated")
							.setData(wareHouseMapper.mapTowareHouseResponse(exwareHouse)));

		}).orElseThrow(() -> new WarehouseNotFoundByIdException("warehouse not found by given id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(Integer wareHouseId) {
		return wareHouseRepository.findById(wareHouseId)
				.map(wareHouse -> ResponseEntity.status(HttpStatus.FOUND)
						.body(new ResponseStructure<WareHouseResponse>().setStatus(HttpStatus.FOUND.value())
								.setMessage("warehouse found by given id")
								.setData(wareHouseMapper.mapTowareHouseResponse(wareHouse))))
				.orElseThrow(() -> new WarehouseNotFoundByIdException("invalid id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouses() {
		List<WareHouseResponse> listofwarehouseresponse = wareHouseRepository.findAll().stream()
				.map(warehouse -> wareHouseMapper.mapTowareHouseResponse(warehouse)).toList();
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WareHouseResponse>>()
				.setStatus(HttpStatus.FOUND.value()).setMessage("warehouses found").setData(listofwarehouseresponse));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouseByCity(String cityName) {
		List<WareHouseResponse> addressesResponses = addressRepository.findAllByCity(cityName).stream()
				.map(address -> wareHouseMapper.mapToWareHouseAddress(address, address.getWareHouse())).toList();

		if (addressesResponses.isEmpty())
			throw new WarehouseNotFoundByCityException("no warehouse found in the city");

		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WareHouseResponse>>()
				.setStatus(HttpStatus.FOUND.value()).setMessage("warehouseFound").setData(addressesResponses));

	}

	@Override
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouseWithNoAdmins() {
		 List<WareHouse> warehouselist= wareHouseRepository.findAll();
		 List<WareHouse> listwarehouse=new ArrayList<WareHouse>();
		for(WareHouse wareHouse:warehouselist) {
			Admin admin = wareHouse.getAdmin();
			if(admin==null) {
				listwarehouse.add(wareHouse);
			}
			}
		List<WareHouseResponse> wareHouseResponses = listwarehouse.stream()
				.map(warehouse -> wareHouseMapper.mapTowareHouseResponse(warehouse)).toList();
	 
			
	
		
	return	ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<WareHouseResponse>>()
				                                       .setStatus(HttpStatus.FOUND.value())
				                                       .setMessage("warehouse found with no admin")
				                                       .setData(wareHouseResponses));
				                                       		
				
			
		 
		
	}
}
