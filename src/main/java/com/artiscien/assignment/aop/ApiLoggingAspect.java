package com.artiscien.assignment.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiLoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

	@Around("execution(* com.artiscien.assignment.controller..*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		//logger.info("Method {} is called with arguments: {}", joinPoint.getSignature().getName(),
				//Arrays.toString(joinPoint.getArgs()));
		Object result;
		try {
			result = joinPoint.proceed();
		} catch (Throwable throwable) {
			//logger.error("Exception in method {}: {}", joinPoint.getSignature().getName(), throwable.getMessage(),
					//throwable);
			throw throwable;
		}
		logger.info("Method {} returns: {}", joinPoint.getSignature().getName(), result);
		return result;
	}

}
