package com.bor.rcms.response;

public class StatusRes {
	 private String status;
	    private String message;
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
			return "StatusRes [status=" + status + ", message=" + message + "]";
		}
		public StatusRes() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
