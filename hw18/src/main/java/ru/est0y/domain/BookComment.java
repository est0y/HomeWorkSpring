package ru.est0y.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("book_comments")
public class BookComment {
    @Id
    private String id;

    private String text;

    @DBRef
    private Book book;

    public BookComment(String text, Book book) {
        this.text = text;
        this.book = book;
    }
}
