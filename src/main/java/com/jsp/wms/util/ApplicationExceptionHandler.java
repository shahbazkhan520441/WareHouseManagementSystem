package com.jsp.wms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.jsp.wms.exception.WarehouseNotFoundByIdException;
import com.jsp.wms.exception.AdminNotFoundByEmail;
import com.jsp.wms.exception.IllegalOperationException;
import com.jsp.wms.exception.AdminNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		return ResponseEntity.status(status)
				.body(new ErrorStructure<String>()
						.setStatus(status.value())
						.setRootCause(rootCause)
						.setMessage(message));
	}
    @ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalOperationException(IllegalOperationException ex){
	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "the super admin is already present");	
	}
    @ExceptionHandler
 	public ResponseEntity<ErrorStructure<String>> WarehouseNotFoundByIdException(WarehouseNotFoundByIdException  ex){
 	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "warehouse not found by given id");	
 	}
    @ExceptionHandler 
   	public ResponseEntity<ErrorStructure<String>>  AdminNotFoundByEmail( AdminNotFoundByEmail  ex){
   	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "admin not found by given email");	
   	}
    @ExceptionHandler 
   	public ResponseEntity<ErrorStructure<String>>  AdminNotFoundByIdException(AdminNotFoundByIdException  ex){
   	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "admin not found by given email");	
   	}
    
}
