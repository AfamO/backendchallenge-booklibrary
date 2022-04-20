package com.polarisdigitech.backendchallenge.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(HttpStatus httpStatus,String s) {

        log.info(" Exception message is:: {}, HttpStatus Code::{} ",s,httpStatus);

    }
}
