package com.cos.aop.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JoinReqDto {
	
	@NotNull(message = "You did not insert any keys for Username")
	@NotBlank(message = "Insert a Username")
	@Size(max = 20, message = "ID is too long")
	private String username;
	
	@NotNull(message = "Insert a Password")
	private String password;
	private String phone;
}
