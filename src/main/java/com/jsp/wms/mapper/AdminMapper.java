package com.jsp.wms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.responsedto.AdminResponse;

@Component
public class AdminMapper {
//	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Admin mapToAdmin(AdminRequest adminRequest,Admin admin) {
		admin.setAdminName(adminRequest.getAdminName());
		admin.setAdminEmail(adminRequest.getAdminEmail());
		admin.setAdminPassword(passwordEncoder.encode(adminRequest.getAdminPassword()) );
		return admin;
	}
	
	public AdminResponse mapToAdminResponse(Admin admin) {
		return AdminResponse.builder()
				.AdminId(admin.getAdminId())
				.AdminName(admin.getAdminName())
				.AdminEmail(admin.getAdminEmail())
				.adminType(admin.getAdminType())
				.build();
	}

}
