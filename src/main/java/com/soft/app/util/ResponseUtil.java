package com.soft.app.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.soft.app.model.APIFailureResponseModel;
import com.soft.app.model.APISuccessResponseModel;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseUtil {

	
	private static final Logger LOGGER = LogManager.getLogger(ResponseUtil.class);

	private static final String API_RESPONSE = "Api response : {}";
	public static final String STATUS_CODE_RESPONSE ="Response_Status_Code:{}";

	@WithSpan(value = "ResponseUtil.setSuccessResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setSuccessResponseStatus(String message, JsonNode node) {
		APISuccessResponseModel apiSuccessResponse = new APISuccessResponseModel();
		apiSuccessResponse.setSuccess(Boolean.TRUE);
		apiSuccessResponse.setMessage(message);
		apiSuccessResponse.setData(node);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE, apiSuccessResponse);
			LOGGER.info(STATUS_CODE_RESPONSE, HttpStatus.OK.toString().replace(" ", "-"));
		}
		return new ResponseEntity<>(apiSuccessResponse, HttpStatus.OK);
	}

	@WithSpan(value = "ResponseUtil.setSuccessResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setSuccessResponseStatus(String message) {

		APISuccessResponseModel apiSuccessResponse = new APISuccessResponseModel();
		apiSuccessResponse.setSuccess(Boolean.TRUE);
		apiSuccessResponse.setMessage(message);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE, apiSuccessResponse);
			LOGGER.info(STATUS_CODE_RESPONSE, HttpStatus.OK.toString().replace(" ", "-"));
		}
		return new ResponseEntity<>(apiSuccessResponse, HttpStatus.OK);
	}

	@WithSpan(value = "ResponseUtil.setSuccessFalseResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setSuccessFalseResponseStatus(String message, JsonNode node) {
		com.soft.app.model.APISuccessResponseModel apiSuccessResponse = new APISuccessResponseModel();
		apiSuccessResponse.setSuccess(Boolean.FALSE);
		apiSuccessResponse.setMessage(message);
		apiSuccessResponse.setData(node);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE, apiSuccessResponse);
			LOGGER.info(STATUS_CODE_RESPONSE, HttpStatus.OK.toString().replace(" ", "-"));
		}
		return new ResponseEntity<>(apiSuccessResponse, HttpStatus.OK);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus, JsonNode node) {

		com.soft.app.model.APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setData(node);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus, String errorCode,
			JsonNode node) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorCode(errorCode);
		apiFailureResponse.setData(node);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus, String errorCode,
			JsonNode node, String errorDescription) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorCode(errorCode);
		apiFailureResponse.setData(node);
		apiFailureResponse.setErrorDescription(errorDescription);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, String errorCode, HttpStatus httpStatus) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorCode(errorCode);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus,String errorCode, String errorDescription) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorCode(errorCode);
		apiFailureResponse.setErrorDescription(errorDescription);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatus httpStatus, String errorDescription) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorDescription(errorDescription);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatus.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatus);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatusCode httpStatusCode) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatusCode.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatusCode);
	}

	@WithSpan(value = "ResponseUtil.setFailureResponseStatus()", kind = SpanKind.CLIENT)
	public ResponseEntity<Object> setFailureResponseStatus(String message, HttpStatusCode httpStatusCode, String errorDescription) {

		APIFailureResponseModel apiFailureResponse = new APIFailureResponseModel();
		apiFailureResponse.setSuccess(Boolean.FALSE);
		apiFailureResponse.setMessage(message);
		apiFailureResponse.setErrorDescription(errorDescription);
		if(LOGGER.isInfoEnabled()) {
			LOGGER.info(API_RESPONSE,apiFailureResponse);
			LOGGER.info(STATUS_CODE_RESPONSE,httpStatusCode.toString().replace(" ","-"));}
		return new ResponseEntity<>(apiFailureResponse, httpStatusCode);
	}




}
