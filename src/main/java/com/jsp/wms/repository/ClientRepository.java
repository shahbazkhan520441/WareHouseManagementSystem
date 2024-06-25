package com.jsp.wms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.jsp.wms.entity.Client;
import com.jsp.wms.util.ResponseStructure;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByEmail(String username);
}
