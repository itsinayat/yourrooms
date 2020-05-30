package com.inayat.yourrooms.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class ApiResponse {

	private int statusCode;
	private String message;
	private List<Object> data;

	public ApiResponse(int status, String message, Object data) {
		this.statusCode = status;
		this.message = message;
		List<Object> datalist = new ArrayList<>();
		datalist.add(data);
		this.data = datalist;
	}

	public ApiResponse(int status, String message) {
		this.statusCode = status;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiResponse [statusCode=" + statusCode + ", message=" + message + "]";
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}


}
