package com.paulo.retry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rholder.retry.Retryer;

@Service
public class TokenService {

	@Autowired
	private Retryer<Object> retryer;
	
	public String refreshToken() throws Exception {
		throw new Exception();
//		return "token";
	}
	
	public void evictCache() {
		System.out.println("Evicting cache...");
	}
}
