package com.jsp.wms.util;

import lombok.Getter;

@Getter
public class SimpleStructure<T> {
	private int status;
	private String message;
	public SimpleStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	public SimpleStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
}
