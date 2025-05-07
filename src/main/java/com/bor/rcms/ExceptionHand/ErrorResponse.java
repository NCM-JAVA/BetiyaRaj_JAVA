package com.bor.rcms.ExceptionHand;

public class ErrorResponse {
	private String message;
	private int status;
	private  long timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", status=" + status + ", timestamp=" + timestamp + "]";
	}
	public ErrorResponse(String message, int status, long timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

}
