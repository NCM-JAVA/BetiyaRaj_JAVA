package com.bor.rcms.GlobalException;

public class StatusResponse {
	private int code;
	private String message;
     public StatusResponse() {
    }

     public StatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

     public int getCode() {
        return code;
    }

     public void setCode(int code) {
        this.code = code;
    }

     public String getMessage() {
        return message;
    }

     public void setMessage(String message) {
        this.message = message;
    }

     @Override
    public String toString() {
        return "StatusResponse [code=" + code + ", message=" + message + "]";
    }
}
