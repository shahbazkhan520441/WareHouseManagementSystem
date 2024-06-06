package com.jsp.wms.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.wms.exception.IllegalOperationException;

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
}
