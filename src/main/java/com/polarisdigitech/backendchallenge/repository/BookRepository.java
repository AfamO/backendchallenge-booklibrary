package com.polarisdigitech.backendchallenge.repository;

import com.polarisdigitech.backendchallenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {
    
   @Query("SELECT b FROM Book b where lower(b.author) = LOWER(:author) ")
   List<Book> selectMyBookByAuthor(String author);
   @Query("SELECT b FROM Book b WHERE LOWER(b.gender) = LOWER(:gender)")
   List<Book> selectMyBookByGender(String gender);
   @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
   Book selectOneBook(String isbn);

}
