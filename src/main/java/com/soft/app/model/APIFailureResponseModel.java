package com.soft.app.model;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class APIFailureResponseModel {

	private Boolean success;

	private String message;

	private String errorCode;

	private JsonNode data;

	private String errorDescription;

	

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "APIFailureResponse [success=" + success + ", message=" + message + ", errorCode=" + errorCode
				+ ", data=" + data + ", errorDescription=" + errorDescription + "]";
	}

	
}
