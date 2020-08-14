package com.infrrd.processManagement.dto;

public class Response {
	
	private String status;
	private String message;
	
	public Response(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Response() {
		super();
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseEntity [status=" + status + ", message=" + message + "]";
	}

	
	

}
