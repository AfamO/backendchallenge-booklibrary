package com.polarisdigitech.backendchallenge.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class TrackOperation {
    @Pointcut("execution(* Operation.*(..))")
    public void logging(){} //pointcut name

    @Before("logging()")
    public void myAdvice(JoinPoint joinPoint){ //Before advice
        System.out.println("Logging before calling "+joinPoint.toShortString());
       System.out.println("Method Signature: "+joinPoint.getSignature());
    }

    @Pointcut("execution(* com.polarisdigitech.backendchallenge.repository.product.ProductRepository.*(..))")
    public void dbLogged(){ }

    @Before("dbLogged()")
    public void myDBBeforeAdvice(JoinPoint joinPoint){
        log.info("Logging: ProductRepository DB Operation is about to start");
    }

    @After("dbLogged()")
    public void myDBAfterAdvice(JoinPoint joinPoint){
        log.info("Logging: ProductRepository DB Operation just ended");
    }

}
