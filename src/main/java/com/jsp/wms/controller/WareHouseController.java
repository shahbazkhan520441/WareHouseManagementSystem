package com.jsp.wms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.wms.entity.WareHouse;
import com.jsp.wms.requestdto.WareHouseRequest;
import com.jsp.wms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
@GetMapping("/warehouse")	
public String createWareHouse(){
	return "warehouse found";
}
}
