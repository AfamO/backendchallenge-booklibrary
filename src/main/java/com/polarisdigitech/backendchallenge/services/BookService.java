package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.model.Book;
import com.polarisdigitech.backendchallenge.repository.BookRepository;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import com.polarisdigitech.backendchallenge.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public BookResponse addBooks(BookRequest bookRequest) {
        if (bookRequest != null) {
            Book book = new Book();
            book.setAuthor(bookRequest.getAuthor());
            book.setCountry(bookRequest.getCountry());
            book.setIsbn(bookRequest.getIsbn());
            book.setTitle(bookRequest.getTitle());
            book.setPublisher(bookRequest.getPublisher());
            bookRepository.save(book);
            return new BookResponse();
        }
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus(500);
        bookResponse.setMessage("Failed");
        return bookResponse;
    }

    public Book findABook(String isbn) {
        if (isbn != null && !isbn.equals(" ")) {
            if (bookRepository.findById(isbn).isPresent()) {
                return bookRepository.findById(isbn).get();
            } else {
                return null;
            }
        }
        return null;
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public boolean updateBook(BookRequest bookRequest) {
        if (bookRequest != null) {
            if (bookRepository.findById(bookRequest.getIsbn()).isPresent()) {
                Book book = bookRepository.findById(bookRequest.getIsbn()).get();
                book.setAuthor(bookRequest.getAuthor());
                book.setCountry(bookRequest.getCountry());
                book.setIsbn(bookRequest.getIsbn());
                book.setTitle(bookRequest.getTitle());
                book.setPublisher(bookRequest.getPublisher());
                bookRepository.save(book);
                return true;

            }
        }
        return false;
    }

    public boolean deleteABook(String isbn) {
        if (isbn != null && !isbn.equals(" ")) {
            if (bookRepository.findById(isbn).isPresent()) {
                bookRepository.deleteById(isbn);
                return true;
            }
        }
        return false;
    }
}