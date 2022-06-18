package com.polarisdigitech.backendchallenge.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Operation {
    public void msg(){
        System.out.println("msg method invoked");
    }
    public void m(){
        System.out.println("m method invoked");
    }
    public void k(){
        System.out.println("k method invoked");
    }
}
