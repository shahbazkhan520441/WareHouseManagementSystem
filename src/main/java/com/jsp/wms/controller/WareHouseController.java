package com.jsp.wms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.util.ResponseStructure;

@RestController
public class WareHouseController {
	
public String createWareHouse(WareHouseRequest wareHouseRequest){
	return "warehouse created";
}
}
