package com.project.Farmer.Support.System.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class LoggingAspects {

    @Around("execution(* com.project.Farmer.Support.System.Service..*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint)throws Throwable{
        String method=joinPoint.getSignature().toShortString();
        Object[]args=joinPoint.getArgs();
        log.debug("Method Started  {}| Args{}", method, Arrays.toString(args));
        long start=System.currentTimeMillis();

        Object result =joinPoint.proceed();

        long end=System.currentTimeMillis();
        log.info("✅ Method Finished: {} | Result: {}", method, result);
        log.info("⏱ Duration: {} ms", end - start);
        return result;

    }
}
