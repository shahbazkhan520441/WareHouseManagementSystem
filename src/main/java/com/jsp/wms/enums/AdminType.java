package com.jsp.wms.enums;

import java.util.List;

public enum AdminType {
SUPER_ADMIN(List.of(AdminPrivileges.CREATED_ADMIN,AdminPrivileges.CREATE_WAREHOUSE,AdminPrivileges.CREATE_ADDRESS,AdminPrivileges.CREATE_STORAGE,AdminPrivileges.READ,AdminPrivileges.UPDATE_ADMIN,AdminPrivileges.UPDATE_WAREHOUSE,AdminPrivileges.UPDATE_ADDRESS,AdminPrivileges.UPDATE_STORAGE,AdminPrivileges.DEL_ADMIN,AdminPrivileges.DEL_WAREHOUSE,AdminPrivileges.DEL_ADDRESS,AdminPrivileges.DEL_STORAGE)),
ADMIN(List.of(AdminPrivileges.CREATE_STORAGE,AdminPrivileges.READ,AdminPrivileges.CREATED_ADMIN,AdminPrivileges.UPDATE_STORAGE,AdminPrivileges.DEL_STORAGE));
	private List<AdminPrivileges>  adminPrivileges;
	
	 AdminType(List<AdminPrivileges> adminPrivileges) {
		 this.adminPrivileges=adminPrivileges;
	}

	public List<AdminPrivileges> getAdminPrivileges() {
		return this.adminPrivileges;
	}
}
