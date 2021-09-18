package com.cos.aop.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		users.add(new User(1, "ssar","1234","0102222"));
		users.add(new User(2, "cos","1234","0102222"));
		users.add(new User(3, "love","1234","0102222"));
		return users;
	}
	
	public User findbyId(int id){
		return new User(1, "ssar","1234","0102222");
	}
	
	
	public void save(JoinReqDto dto) {
		System.out.println("Insert Success!");
	}
	
	public void delete(int id) {
		System.out.println("Delete Success!");
	}
	
	public void update(int id, UpdateReqDto dto) {
//		System.out.println("Updata Success!");
		throw new IllegalArgumentException("Wrong");
	}
}
