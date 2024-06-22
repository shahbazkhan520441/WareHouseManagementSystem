package com.jsp.wms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.wms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

     

	public List<Address> findAllByCity(String cityName);
	

}
