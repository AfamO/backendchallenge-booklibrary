package com.polarisdigitech.backendchallenge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
    log.info("Exception Cause::{}",throwable.getMessage());
    log.info("Method Name:: {}",method.getName());
    for (Object param: objects)
        log.info("Parameter Value::{}",param);
    }
}
