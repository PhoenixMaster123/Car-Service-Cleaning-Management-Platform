package com.example.carservice.common.config.logger;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Modern AOP logger that logs method entry, exit, execution time, and exceptions
 * for specified pointcuts using a single @Around advice.
 */
@Aspect
@Component
@Slf4j
public class AopLogger {

    /**
     * Pointcut that matches all beans in the carservice package and its sub-packages.
     * Using ..* includes sub-packages like .controllers, .services, etc.
     */
    @Pointcut("within(com.example.carservice..*)")
    public void applicationPackagePointcut() {}

    /**
     * A single @Around advice to log method entry, exit, exceptions, and execution time.
     * This replaces the need for @Before, @AfterReturning, and @AfterThrowing.
     *
     * @param joinPoint The proceeding join point.
     * @return The result of the method execution.
     * @throws Throwable if the underlying method throws an exception.
     */
    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String typeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("➡️ {}.{}() with args: {}", typeName, methodName, Arrays.toString(joinPoint.getArgs()));

        long startTime = System.currentTimeMillis();
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            long timeTaken = System.currentTimeMillis() - startTime;
            log.error("❌ {}.{}() threw exception [{} ms]: {}", typeName, methodName, timeTaken, ex.getMessage(), ex);
            throw ex;
        }

        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("✅ {}.{}() with result: {} [{} ms]", typeName, methodName, result, timeTaken);

        return result;
    }
}