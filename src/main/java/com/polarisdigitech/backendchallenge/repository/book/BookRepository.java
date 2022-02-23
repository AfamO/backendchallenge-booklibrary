package com.polarisdigitech.backendchallenge.repository.book;

import com.polarisdigitech.backendchallenge.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {


   @Query("SELECT b FROM Book b where lower(b.author) = LOWER(:author) ")
   List<Book> selectMyBookByAuthor(String author);
   @Query("SELECT b FROM Book b WHERE LOWER(b.gender) = LOWER(:gender)")
   List<Book> selectMyBookByGender(String gender);
   @Query("SELECT b FROM Book b WHERE b.gender = ?1")
   List<Book> selectBookByGender(String isbn);
   @Query("SELECT b FROM Book b WHERE b.title=:title")
   Book selectByTitle(String title);
   @Query("SELECT b FROM Book b WHERE b.isbn =?1 AND b.gender =?2")
   Book selectByIsbnAndGender(String isbn, String gender);
   @Query("SELECT book FROM Book book WHERE book.isbn=:isbn AND book.country=:country")
   Book selectByIsbnAndCountry(@Param("isbn")String myIsbn,@Param("country")String myCountry);
   @Query(value = "SELECT * FROM Book b WHERE b.country=:country", nativeQuery = true, countQuery = "SELECT count(*) WHERE country=?1")
   Page<List<Book>> selectBookByCountry(String country, Pageable pageable);
   @Query(value = "SELECT * FROM Book book WHERE book.gender IN :genderList",nativeQuery = true)
   List<Book> selectBooksHavingParticularGender(List<String> genderList);
   @Transactional
   @Modifying
   @Query("UPDATE Book book SET book.publisher =:publisher WHERE book.isbn = :isbn")
   int updateCount(@Param("publisher")String myPublisher, String isbn);

}
