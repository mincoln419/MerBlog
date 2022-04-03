/**
 * @packageName : org.springframework.samples.petclinic.aspect
 * @fileName : LogAspect.java
 * @author : Mermer
 * @date : 2022.04.03
 * @description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2022.04.03 Mermer 최초 생성
 */
package org.springframework.samples.petclinic.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
 * @description:
 */
@Component
@Aspect
public class LogAspect {

	Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());

		return proceed;
	}

}
