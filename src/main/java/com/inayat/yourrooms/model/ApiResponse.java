package com.inayat.yourrooms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_DEFAULT)
public class ApiResponse {

	private int statusCode;
	private String message;
	private Object data;

	public ApiResponse(int status, String message, Object data){
	    this.statusCode = status;
	    this.message = message;
	    this.data = data;
    }

    public ApiResponse(int status, String message){
        this.statusCode = status;
        this.message = message;
    }

    public int getStatus() {
        return statusCode;
    }

    public void setStatus(int status) {
        this.statusCode = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

   

    @Override
	public String toString() {
		return "ApiResponse [statusCode=" + statusCode + ", message=" + message +"]";
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
