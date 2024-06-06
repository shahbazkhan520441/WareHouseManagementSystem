package com.jsp.wms.requestdto;

import java.util.List;

import com.jsp.wms.enums.AdminPrivileges;
import com.jsp.wms.enums.AdminType;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdminRequest {
	
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	
	

}
