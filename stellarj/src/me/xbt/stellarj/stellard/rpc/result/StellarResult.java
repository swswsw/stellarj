package me.xbt.stellarj.stellard.rpc.result;

import flexjson.JSONSerializer;

/**
 * 
 * the result could also be an error response.  
 * eg. {   "result" : {      "error" : "invalidParams",      "error_code" : 27,      "error_message" : "Missing field 'taker_pays'.",      "request" : {         "command" : "book_offers"      },      "status" : "error"   }}
 * 
 * @author kaye wu
 *
 */
public class StellarResult {
	/** to show a value is uninitiated */
	public static final int UNINITIATED = -2;

	private String status = null;
	
	// for error response.  these will only be populated when status == "error"
	private String error = null;
	private Long error_code = null;
	private String error_message = null;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getError_code() {
		return error_code;
	}

	public void setError_code(Long error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	
	public String toString() {
		return new JSONSerializer().prettyPrint(true).deepSerialize(this);
	}
}
