package com.artiscien.assignment.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.artiscien.assignment.service.ApplicationHealthService;

@Configuration
@EnableScheduling
public class ApplicationHealthScheduler {

	@Autowired
	private ApplicationHealthService applicationHealthService;

	@Scheduled(fixedRate = 60000)
	public void captureSystemHealth() {
		applicationHealthService.captureAndSaveSystemHealth();
	}
}
