package com.bor.rcms.resonse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseSet{
    public static ResponseEntity<?> generateResponse(String message, HttpStatus status, Object responseObj) {
        ResponseWrapper wrapper = new ResponseWrapper(message, status.value(), responseObj);
        return new ResponseEntity<>(wrapper, status);
    }

    public static ResponseEntity<?> generateResponse(String message, HttpStatus status) {
        ResponseWrapper wrapper = new ResponseWrapper(message, status.value(), null);
        return new ResponseEntity<>(wrapper, status);
    }

    private static class ResponseWrapper {
        private final String message;
        private final int status;
        private final Object data;

        public ResponseWrapper(String message, int status, Object data) {
            this.message = message;
            this.status = status;
            this.data = data;
        }

        
        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        public Object getData() {
            return data;
        }
    }
}