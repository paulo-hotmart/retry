package com.paulo.retry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

@Service
public class RetryListenerService implements RetryListener {

	@Autowired
	private TokenService tokenService;
	
	@Override
	public <V> void onRetry(Attempt<V> attempt) {
		if(!attempt.hasResult()) {
			System.out.println("Attempt failed. Will retry again.");
			tokenService.evictCache();
		} else {
			System.out.println("Attempt succesful. Will not retry anymore.");
		}
	}
}
