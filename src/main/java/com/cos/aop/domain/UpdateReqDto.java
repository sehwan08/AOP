package com.cos.aop.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateReqDto {
	
	@NotBlank(message = "Did not put any password")
	private String password;
	private String phone;
}
