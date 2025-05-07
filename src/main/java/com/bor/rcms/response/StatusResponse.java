package com.bor.rcms.response;

import java.util.Optional;

public class StatusResponse <E> {
    private String status;  
    private String message;
    private Optional<?> option = Optional.empty(); // default to empty

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

    public Optional<?> getOption() {
        return option;
    }

    public void setOption(E option) {
        this.option = Optional.ofNullable(option);
    }

	@Override
	public String toString() {
		return "StatusResponse [status=" + status + ", message=" + message + ", option=" + option + "]";
	}
    
    
}