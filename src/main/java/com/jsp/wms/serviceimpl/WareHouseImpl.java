package com.jsp.wms.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.WareHouseMapper;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.WareHouseRequest;
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
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//	    for (GrantedAuthority grantedAuthority : authorities) {
//			if(grantedAuthority.getAuthority().equals("CREATE_WAREHOUSE")) {
//				WareHouse wareHouse=wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
//				wareHouse=wareHouseRepository.save(wareHouse);
//			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>(
//					).setStatus(HttpStatus.CREATED.value())
//					.setMessage("warehouse created")
//					.setData(wareHouseMapper.mapTowareHouseResponse(wareHouse)));
//			}
//			
//		}
//		return null; ISTED OF ABOVE LOGIC FOR SECURITY BEFORE ACCES WE USE @PreAuthorize annoation
		
		WareHouse wareHouse=wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
		wareHouse=wareHouseRepository.save(wareHouse);
	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<WareHouseResponse>()
			                                                    .setStatus(HttpStatus.CREATED.value())
			                                                        .setMessage("warehouse created")
			                                                        .setData(wareHouseMapper.mapTowareHouseResponse(wareHouse)));
		            
	 
	}
	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,
			int wareHouseId) {
		return wareHouseRepository.findById(wareHouseId).map(exwareHouse->{
		WareHouse warehouse=wareHouseMapper.mapToWareHouse(wareHouseRequest, exwareHouse);
		exwareHouse=wareHouseRepository.save(warehouse);
		 return  ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseStructure<WareHouseResponse>()
						.setStatus(HttpStatus.OK.value())
						.setMessage("warehouse updated")
						.setData(wareHouseMapper.mapTowareHouseResponse(exwareHouse)));
		
		}).orElseThrow(()->new WarehouseNotFoundByIdException("warehouse not found by given id") );
		}

	}


