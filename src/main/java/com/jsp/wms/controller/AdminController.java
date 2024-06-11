package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.AdminRequest;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.responsedto.AdminResponse;
import com.jsp.wms.responsedto.WareHouseResponse;
import com.jsp.wms.service.AdminService;
import com.jsp.wms.service.WareHouseService;
import com.jsp.wms.util.ResponseStructure;
@RestController
@RequestMapping("/api/v1")
public class AdminController {
	@Autowired
	AdminService adminService;

	@PostMapping("/register")//only for one time registration of super admin
	public ResponseEntity<ResponseStructure<AdminResponse>> createSuperAdmin(@RequestBody AdminRequest adminRequest){
		return adminService.createSuperAdmin(adminRequest);
	}
	@PostMapping( "/warehouse/{wareHouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(@RequestBody AdminRequest adminRequest,@PathVariable Integer wareHouseId){
		return adminService.createAdmin( adminRequest,wareHouseId);
	}
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest){
		return adminService. updateAdmin(adminRequest);
	}
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@RequestBody AdminRequest adminRequest,@PathVariable Integer adminId){
		return adminService.updateAdminBySuperAdmin(adminRequest,adminId);
	}
	

	
	
	

}
