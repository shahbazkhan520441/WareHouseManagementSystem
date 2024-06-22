package com.jsp.wms.mapper;

import java.nio.channels.ClosedByInterruptException;

import org.springframework.stereotype.Component;

import com.jsp.wms.entity.Client;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ClientResponse;
@Component
public class ClientMapper {
public Client mapToClent(ClientRequest clientRequest,Client client) {
	client.setBusinessName(clientRequest.getBusinessName());
	client.setContactNumber(clientRequest.getContactNumber());
	client.setEmail(clientRequest.getEmail());
	return client;
	
}



public ClientResponse mapToClientResponse(Client client) {
	return ClientResponse.builder()
			.buisnessName(client.getBusinessName())
			.contactNumber(client.getContactNumber())
			.email(client.getEmail())
			.build();
}

}
