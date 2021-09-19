package com.cos.aop.config;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cos.aop.domain.CommonDto;

@Component
@Aspect
public class BindingAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);
	
	@Before("execution(* com.cos.aop.web..*Controller.*(..))")
	public void beforeTest() {
		
		
		HttpServletRequest request =
				((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.out.println("주소: "+request.getRequestURL());
		
		System.out.println("Before-Log saved.");
	}
	
	@After("execution(* com.cos.aop.web..*Controller.*(..))")
	public void afterTest() {
		System.out.println("After-Log saved.");
	}
	
	
	//@Before(앞)
	//@After(뒤)
	@Around("execution(* com.cos.aop.web..*Controller.*(..))")
	public Object validationCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { //메서드에 있는 모든걸 다들고와서 넣음
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type: "+type);
		System.out.println("method: "+method);
		
		Object[] args = proceedingJoinPoint.getArgs();
		
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult)arg;
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error:bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
						log.warn(type+"."+method+"() => 필드 :"+error.getField()+", 메시지: "+error.getDefaultMessage());
						log.debug(type+"."+method+"() => 필드 :"+error.getField()+", 메시지: "+error.getDefaultMessage());
					}
					
					return new CommonDto<>(HttpStatus.BAD_REQUEST.value(),errorMap);
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}