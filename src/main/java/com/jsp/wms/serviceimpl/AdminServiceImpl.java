package com.jsp.wms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.enums.AdminPrivileges;
import com.jsp.wms.enums.AdminType;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.mapper.AdminMapper;
import com.jsp.wms.repository.Repository;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.util.ResponseStructure;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	Repository adminRepository;
	@Autowired
	AdminMapper adminMapper;
	

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest) {
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
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest) {
		return null;
	}

}
