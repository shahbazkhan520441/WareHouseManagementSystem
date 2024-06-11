package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class AdminNotFoundByEmail extends RuntimeException {
private String meassage;
}
