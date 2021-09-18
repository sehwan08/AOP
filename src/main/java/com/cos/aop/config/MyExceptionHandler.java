package com.cos.aop.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String IllegarCall(IllegalArgumentException e) {
		return "오류: "+e.getMessage();
	}
}
