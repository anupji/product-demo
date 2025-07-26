package com.soft.app.model;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class APISuccessResponseModel {

	private Boolean success;

	private String message;

	private JsonNode data;

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

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "APISuccessResponse [success=" + success + ", message=" + message + ", data=" + data + "]";
	}

	
}
