package com.jsp.wms.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.wms.entity.Client;
import com.jsp.wms.exception.ClientNotFoundByIdException;
import com.jsp.wms.mapper.ClientMapper;
import com.jsp.wms.repository.ClientRepository;
import com.jsp.wms.requestdto.ClientRequest;
import com.jsp.wms.responsedto.ClientResponse;
import com.jsp.wms.service.ClientService;
import com.jsp.wms.util.ResponseStructure;
@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
    ClientRepository clientRepository;
	@Autowired
	ClientMapper clientMapper;
	@Override
	public ResponseEntity<ResponseStructure<String>> registerClient(ClientRequest clientRequest) {
	     Client client = clientMapper.mapToClent(clientRequest, new Client());
	     client.setApiKey(UUID.randomUUID().toString());
		client=clientRepository.save(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<String>()
				                                               .setStatus(HttpStatus.CREATED.value())
				                                               .setMessage("client registered sucesully")
				                                               .setData("apiKey:"+client.getApiKey()));
	}
	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest,
			Integer clientId) {
	return	clientRepository.findById(clientId).map(exclient->{
			Client client=clientMapper.mapToClent(clientRequest, exclient);
			clientRepository.save(client);
			
		return	ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<ClientResponse>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("clent updated sucessfully")
					.setData(clientMapper.mapToClientResponse(client)));
		}).orElseThrow(()->new ClientNotFoundByIdException("invalid client id "));
		   
		
	
	}
	

}
