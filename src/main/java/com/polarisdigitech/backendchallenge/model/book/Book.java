package com.polarisdigitech.backendchallenge.model.book;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "book")
public class Book {

    @Id
    //@NotNull(message ="ISBN is compulsory")
    private String isbn;

    public Book(String s, String how_to_program, String deitel_deitel, String female, String deitel_inc, String nigeria) {
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", gender='" + gender + '\'' +
                ", publisher='" + publisher + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    private String title;
    private String author;
    private String gender;
    private String publisher;
    private String country;
    @Column(nullable = true, length = 64)
    private String photo;

}
