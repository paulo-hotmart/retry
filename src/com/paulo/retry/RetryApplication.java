package com.paulo.retry;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.paulo.retry.service.MyService;
import com.paulo.retry.service.RetryListenerService;

@SpringBootApplication
public class RetryApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RetryApplication.class);
		
		MyService bean = applicationContext.getBean(MyService.class);
		
		try {
			bean.tenta();
		} catch (ExecutionException | RetryException e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Retryer<Object> retrier(RetryListenerService retryListener) {
		Retryer<Object> retryer = RetryerBuilder.newBuilder()
				.retryIfException()
				.withStopStrategy(StopStrategies.stopAfterAttempt(3))
				.withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
				.withRetryListener(retryListener)
			.build();
		
		return retryer;
	}
}
