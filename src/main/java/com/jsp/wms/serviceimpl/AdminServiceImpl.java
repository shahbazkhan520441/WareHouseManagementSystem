package com.jsp.wms.serviceimpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.enums.AdminPrivileges;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.mapper.WareHouseMapper;
import com.jsp.wms.repository.Repository;
import com.jsp.wms.repository.WareHouseRepository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.util.ResponseStructure;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	Repository adminRepository;
	@Autowired
	WareHouseRepository wareHouseRepository;
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	WareHouseMapper wareHouseMapper;
	

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(AdminRequest adminRequest) {
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN)) {
			throw new IllegalOperationException("super admin allreddy exist");
		}
		Admin admin=adminMapper.mapToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		admin=adminRepository.save(admin);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("admincreated")
						.setData(adminMapper.mapToAdminResponse(admin)));
	}


	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest,
			Integer wareHouseId) {
		return wareHouseRepository.findById(wareHouseId).map(wareHouse-> {
			Admin admin=adminMapper.mapToAdmin(adminRequest, new  Admin());
			admin.setAdminType(AdminType.ADMIN);
			admin=adminRepository.save(admin);
		    wareHouse.setAdmin(admin);
			wareHouseRepository.save(wareHouse);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.CREATED.value())
									.setMessage("admin created")
									.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(()-> new WarehouseNotFoundByIdException("Not found"));
	
	}









}
