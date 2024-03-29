
package com.polarisdigitech.backendchallenge.services;

import com.polarisdigitech.backendchallenge.model.book.Book;
import com.polarisdigitech.backendchallenge.repository.book.BookRepository;
import com.polarisdigitech.backendchallenge.request.BookRequest;
import com.polarisdigitech.backendchallenge.request.XMLBookRequest;
import com.polarisdigitech.backendchallenge.response.BookResponse;
import com.polarisdigitech.backendchallenge.response.XMLBookResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;

    @Autowired
    private  ModelMapper mapper;

    public BookResponse addBooks(BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse();
        if (bookRequest != null) {
            if(bookRepository.findById(bookRequest.getIsbn()).isPresent()){
                bookResponse.setStatus(500);
                bookResponse.setMessage("Error: The book with this ISBN, already exists");
                return bookResponse;
            }
            Book book = new Book();
            book.setAuthor(bookRequest.getAuthor());
            book.setCountry(bookRequest.getCountry());
            book.setIsbn(bookRequest.getIsbn());
            book.setTitle(bookRequest.getTitle());
            book.setPublisher(bookRequest.getPublisher());
            book.setGender(bookRequest.getGender());
            book = bookRepository.save(book);
            bookResponse.setData(book);
            return bookResponse;
        }
        bookResponse.setStatus(500);
        bookResponse.setMessage("Failed");
        return bookResponse;
    }

    public BookResponse findABook(String isbn) {
        BookResponse bookResponse = new BookResponse();
        if (isbn != null && !isbn.equals(" ")) {
            if (bookRepository.findById(isbn).isPresent()) {
                bookResponse.setData(bookRepository.findById(isbn).get());
                return bookResponse;
            }
            else{
                bookResponse.setStatus(404);
                bookResponse.setMessage("Error: Book not found");
                return bookResponse;
            }
        }
        bookResponse.setStatus(500);
        bookResponse.setMessage("Failed");
        return bookResponse;
    }

    public Iterable<Book> getAllBooks(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }

    public Book getOne(String isbn){
        return bookRepository.getOne(isbn) ;
    }
    
    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public XMLBookResponse getXMLBook(XMLBookRequest xmlBookRequest){
        XMLBookResponse xmlBookResponse = new XMLBookResponse(xmlBookRequest.getIsbn(),xmlBookRequest.getTitle(), xmlBookRequest.getAuthor());
        return xmlBookResponse;
        //return mapper.map(xmlBookRequest,XMLBookResponse.class);
    }
    public List<Book> getBookByGender(String gender) {
        return bookRepository.selectMyBookByGender(gender);
    }

    public BookResponse updateBook(BookRequest bookRequest) {
        BookResponse bookResponse = new BookResponse();
        if (bookRequest != null) {
            if (bookRepository.findById(bookRequest.getIsbn()).isPresent()) {
                Book book = bookRepository.findById(bookRequest.getIsbn()).get();
                book.setAuthor(bookRequest.getAuthor());
                book.setCountry(bookRequest.getCountry());
                book.setIsbn(bookRequest.getIsbn());
                book.setTitle(bookRequest.getTitle());
                book.setPublisher(bookRequest.getPublisher());
                book.setGender(bookRequest.getGender());
                book = bookRepository.save(book);
                bookResponse.setData(book);
                return bookResponse;

            }
            else {
                bookResponse.setStatus(404);
                bookResponse.setMessage("Error: Book with this ISBN not found");
                return bookResponse;
            }
        }
        bookResponse.setStatus(500);
        bookResponse.setMessage("Failed");
        return bookResponse;
    }

    public BookResponse deleteABook(String isbn) {
        BookResponse bookResponse = new BookResponse();
        if (isbn != null && !isbn.equals(" ")) {
            if (bookRepository.findById(isbn).isPresent()) {
                bookRepository.deleteById(isbn);
                return new BookResponse();
            }
            else{
                bookResponse.setStatus(404);
                bookResponse.setMessage("Error: ISBN not found");
                return bookResponse;
            }
        }
        bookResponse.setStatus(500);
        bookResponse.setMessage("Failed");
        return bookResponse;
    }
    @Bean
    private ModelMapper getMapper(){
        return new ModelMapper();
    }
}