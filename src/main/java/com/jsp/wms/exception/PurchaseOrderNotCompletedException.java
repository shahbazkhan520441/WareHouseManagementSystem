package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PurchaseOrderNotCompletedException extends RuntimeException{
private String message;
}
