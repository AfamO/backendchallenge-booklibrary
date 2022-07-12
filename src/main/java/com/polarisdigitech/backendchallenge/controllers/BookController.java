
package com.polarisdigitech.backendchallenge.controllers;

import com.polarisdigitech.backendchallenge.helpers.Constants;
import com.polarisdigitech.backendchallenge.model.book.Book;
import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import com.polarisdigitech.backendchallenge.response.BookResponse;
import com.polarisdigitech.backendchallenge.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(Constants.API_PATH+"books")
public class BookController {


    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/addBook")
    public BookResponse addBook(@RequestBody BookRequest bookRequest) {
        log.info("Attempting to add a book with isbn: {} ",bookRequest.getIsbn());

        return this.bookService.addBooks(bookRequest);
    }

    @PutMapping("/updateBook")
    public BookResponse updateBook(@RequestBody BookRequest bookRequest) {

        return this.bookService.addBooks(bookRequest);
    }

    @GetMapping("")
    private List<Book> getBooks() {
        Sort sort = Sort.by(Sort.Direction.ASC,"author");
        Pageable pageable = PageRequest.of(0,4,sort);
        Page<Book> listPage = bookRepository.selectBookByCountry("Nigeria", PageRequest.of(0,5));
        return  listPage.getContent();
        //return bookService.getAllBooks(pageable); // for now. Just a demo. It is supposed  be  paginated in real life.
    }


    @GetMapping("/by/{author}")
    private List<Book> getBooksByAuthor(@PathVariable("author") String author) {
        log.info("Getting by author {}",author);
        log.info("By Author =={}",bookService.getBookByAuthor(author));
        return bookService.getBookByAuthor(author);
    }
    @GetMapping("/by/gender/{gender}")
    private List<Book> getBooksByGender(@PathVariable String gender) {
        return bookService.getBookByGender(gender);
    }
    @GetMapping("/{isbn}")
    private BookResponse getBook(@PathVariable("isbn")String isbn) {
        return bookService
                //.getOne(isbn) ;
                .findABook(isbn); // normally there should be validation, this is just demo.

    }

    @DeleteMapping("/{isbn}")
    private BookResponse deleteABook(@PathVariable("isbn") String isbn) {
        return bookService.deleteABook(isbn);
    }
}