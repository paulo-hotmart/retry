package com.paulo.retry.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;

@Service
public class MyService {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private Retryer<Object> retryer;
	
	public void tenta() throws ExecutionException, RetryException {
		retryer.call(() -> {
			String token = tokenService.refreshToken();
			System.out.println("token " + token);
			return "ok";
		});
	}
}
