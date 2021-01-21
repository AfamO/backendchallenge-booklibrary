package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.model.Book;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import com.polarisdigitech.backendchallenge.response.BookResponse;
import com.polarisdigitech.backendchallenge.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public BookResponse createTransactions(@RequestBody BookRequest bookRequest){

        return this.bookService.addBooks(bookRequest);
    }

    @RequestMapping(value="/books", method= RequestMethod.GET)
    private Iterable<Book> getBooks(){


        return bookService.getAllBooks(); // for now. Just a demo. It is supposed  be  paginated in real life.
    }


    @RequestMapping("/books/{isbn}")
    private Book getBook(@PathVariable("isbn") String isbn) {
        return bookService.findABook(isbn); // normally there should be validation, this is just demo.
        }
    }
