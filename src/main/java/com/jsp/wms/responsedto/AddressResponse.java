package com.jsp.wms.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
	private Integer addressId;
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private Integer pincode;
	private String longitude;
	private String latitude;

}
