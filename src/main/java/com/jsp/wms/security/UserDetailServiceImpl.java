package com.jsp.wms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Admin;
import com.jsp.wms.repository.Repository;
import com.jsp.wms.responsedto.AdminResponse;
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	Repository adminrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return adminrepository.findByAdminEmail(username)
				.map(UserDetailImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
		
	}


	

}
