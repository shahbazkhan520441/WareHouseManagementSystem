package com.jsp.wms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ClientResponse;
import com.jsp.wms.util.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<String>> registerClient(ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest, Integer clientId);


}
