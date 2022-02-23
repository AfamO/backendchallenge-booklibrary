package com.polarisdigitech.backendchallenge.helpers;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class StorageException extends RuntimeException {
    public StorageException(String s) {
        log.info(" Exception message is:: {} ",s);
    }
}
