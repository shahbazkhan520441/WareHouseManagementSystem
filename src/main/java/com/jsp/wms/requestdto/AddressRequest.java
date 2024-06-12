package com.jsp.wms.requestdto;


import com.jsp.wms.entity.WareHouse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private Integer pincode;
	private String longitude;
	private String latitude;
}
