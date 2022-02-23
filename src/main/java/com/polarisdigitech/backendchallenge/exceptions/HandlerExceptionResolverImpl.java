package com.polarisdigitech.backendchallenge.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("uploadForm");
        if (exception instanceof MaxUploadSizeExceededException){
            modelAndView.getModel().put("message","File size limit exceeded");
        }
        return modelAndView;
    }
}
