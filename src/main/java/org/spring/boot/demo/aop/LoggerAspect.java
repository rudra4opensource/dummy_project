package org.spring.boot.demo.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.spring.boot.demo.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Before("@annotation(org.spring.boot.demo.annotation.LogObjectBefore)")
    public void logCustomerBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Customer) {
                Customer customer = (Customer) arg;
                log.info("******* Customer before :: {}", customer);
            } else {
                log.info("******* Nothing Happened!");
            }
        }
    }

    @AfterReturning(value = "@annotation(org.spring.boot.demo.annotation.LogObjectAfter)", returning = "result")
    public void logCustomerAfter(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        if (Objects.nonNull(result)) {
            if (result instanceof ResponseEntity) {
                ResponseEntity responseEntity = (ResponseEntity) result;

                if (responseEntity.getStatusCode().value() == 200)
                    log.info("******* Returning object :: {}", responseEntity.getBody());
                else
                    log.error("Something went wrong while logging...!");
            }
        }
    }
}
