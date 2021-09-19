package com.cos.aop.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.aop.domain.CommonDto;
import com.cos.aop.domain.JoinReqDto;
import com.cos.aop.domain.UpdateReqDto;
import com.cos.aop.domain.User;
import com.cos.aop.domain.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class UserController {
	
	private final UserRepository userRepository;

	@GetMapping("/user")
	public CommonDto<List<User>> findAll() {
		System.out.println("findAll()");
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findAll());
	}
	
	@GetMapping("/user/{id}")
	public CommonDto<User> findById(@PathVariable int id) {
		System.out.println("findById(): id: " +id);
		return new CommonDto<>(HttpStatus.OK.value(), userRepository.findbyId(id));
	}
	
	@PostMapping("/user")
	public CommonDto<?> save(@Valid @RequestBody JoinReqDto dto,
			 BindingResult bindingResult) {
		System.out.println("save()");
		System.out.println("user: "+dto);
		userRepository.save(dto);
		return new CommonDto<>(HttpStatus.OK.value(),"ok");
	}
	
	@DeleteMapping("/user/{id}")
	public CommonDto delete(@PathVariable int id) {
		System.out.println("delete()");
		userRepository.delete(id);
		return new CommonDto<>(HttpStatus.OK.value());
	}
	
	@PutMapping("/user/{id}")
	public CommonDto update(@PathVariable int id, @Valid @RequestBody UpdateReqDto dto
			,  BindingResult bindingResult) {
		System.out.println("update()");
		userRepository.update(id, dto);
		return new CommonDto<>(HttpStatus.OK.value());
	}
}
