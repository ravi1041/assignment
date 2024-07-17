package com.artiscien.assignment.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiMonitoringAspect {
	private static final Logger logger = LoggerFactory.getLogger(ApiMonitoringAspect.class);

	@Around("execution(* com.example.service..*(..))")
	public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("Method {} executed in {} ms", joinPoint.getSignature().getName(), (end - start));
		return result;
	}

}
