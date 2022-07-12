package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.helpers.Constants;
import com.polarisdigitech.backendchallenge.request.XMLBookRequest;
import com.polarisdigitech.backendchallenge.response.XMLBookResponse;
import com.polarisdigitech.backendchallenge.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.API_PATH+"xml")
@Slf4j
public class XMLConsumerController {

    private final BookService bookService;

    public XMLConsumerController(BookService bookService) {

        this.bookService = bookService;
    }


    @PostMapping(value = "", consumes = {MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_XML_VALUE})
        public XMLBookResponse fetchXmLResponse(@RequestBody XMLBookRequest xmlBookRequest){
        log.info("The requested xmlValue =="+xmlBookRequest);
        return bookService.getXMLBook(xmlBookRequest);
    }
}
