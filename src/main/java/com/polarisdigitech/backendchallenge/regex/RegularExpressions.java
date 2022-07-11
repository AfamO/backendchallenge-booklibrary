package com.polarisdigitech.backendchallenge.regex;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class RegularExpressions {

    public void matchOneOrMoreNumbers(){
        String s = "2314511167";

        log.info("OneorMoreMatcher=="+ Arrays.asList(s.split("1+")));

    }
}
