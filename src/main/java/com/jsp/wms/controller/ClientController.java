package com.jsp.wms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.repository.ClientRepository;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ClientResponse;
import com.jsp.wms.service.ClientService;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ClientService clientService;
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<String>> registerClient(@RequestBody ClientRequest clientRequest){
		return  clientService.registerClient(clientRequest);
	}
	@PutMapping("/clients/{clientId}")
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@RequestBody ClientRequest clientRequest,@PathVariable Integer clientId ){
		return clientService.updateClient(clientRequest,clientId);
	}

}
