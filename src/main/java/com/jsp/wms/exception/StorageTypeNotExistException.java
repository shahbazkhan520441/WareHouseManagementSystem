package com.jsp.wms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ Getter
@AllArgsConstructor
public class StorageTypeNotExistException extends RuntimeException{
private String message;
}
