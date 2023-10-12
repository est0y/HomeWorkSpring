package ru.est0y.services;

import ru.est0y.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    void insert(String name, String authorId, String genreId);

    void update(String  id, String name, String authorId, String genreId);

    Optional<Book> findById(String id);

    List<Book> findAll();

    void deleteById(String  id);
}
