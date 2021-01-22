package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.model.Book;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import com.polarisdigitech.backendchallenge.response.BookResponse;
import com.polarisdigitech.backendchallenge.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public BookResponse addBook(@RequestBody BookRequest bookRequest) {
        log.info("Attempting to add a book with isbn: {} ",bookRequest.getIsbn());
        return this.bookService.addBooks(bookRequest);
    }

    @PutMapping("/updateBook")
    public BookResponse updateBook(@RequestBody BookRequest bookRequest) {

        return this.bookService.addBooks(bookRequest);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    private Iterable<Book> getBooks() {


        return bookService.getAllBooks(); // for now. Just a demo. It is supposed  be  paginated in real life.
    }


    @RequestMapping("/books/{isbn}")
    private BookResponse getBook(@PathVariable("isbn") String isbn) {
        return bookService.findABook(isbn); // normally there should be validation, this is just demo.

    }

    @DeleteMapping("/books/{isbn}")
    private BookResponse deleteABook(@PathVariable("isbn") String isbn) {
        return bookService.deleteABook(isbn);
    }
}