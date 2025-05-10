package com.bor.rcms.ExceptionHand;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AplicationExceptionHandler{
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> ValidateExceptio(MethodArgumentNotValidException exc){
		Map<String,String>ErrorMap=new HashMap<String,String>();
		exc.getBindingResult().getFieldErrors().
		forEach(error->{ErrorMap.put(error.getField(), error.getDefaultMessage());});
		
		
		return ErrorMap;
		}
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	//@ExceptionHandler()
}

