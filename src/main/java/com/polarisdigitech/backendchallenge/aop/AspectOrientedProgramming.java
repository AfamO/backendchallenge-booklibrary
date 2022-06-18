package com.polarisdigitech.backendchallenge.aop;

import org.aspectj.lang.annotation.Pointcut;

public class AspectOrientedProgramming {
    @Pointcut("execution(* Operation.*(..))")
    private void doSomething(){}

    @Pointcut("execution(public Operation.*(..))")
    private void sendNotification(){}

    @Pointcut("execution(int Operation.*(..))")
    private void returnAndLog(){}

    @Pointcut("execution(public void Employee.set*(..))")
    private void set(){

    }
}
