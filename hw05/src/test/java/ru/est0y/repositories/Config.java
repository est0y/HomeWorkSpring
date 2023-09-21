package ru.est0y.repositories;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;


public class Config {
    public static final Author EXPECTED_AUTHOR = new Author(1L, "Author 1");
    public static final Genre EXPECTED_GENRE = new Genre(1L, "Genre 1");
    public static final Book EXPECTED_BOOK = new Book(1L, "Book 1", EXPECTED_AUTHOR, EXPECTED_GENRE);
}
