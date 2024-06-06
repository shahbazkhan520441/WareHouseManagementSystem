package com.jsp.wms.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.wms.entity.Admin;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImpl implements UserDetails {

	private Admin admin;  

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return admin.getAdminType().getAdminPrivileges().stream()
				.map(privlage -> new SimpleGrantedAuthority(privlage.name())).toList();

	}

	@Override
	public String getPassword() {
		return admin.getAdminPassword();
	}

	@Override
	public String getUsername() {
		return admin.getAdminEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
