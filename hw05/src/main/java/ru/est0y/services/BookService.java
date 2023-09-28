package ru.est0y.services;

import ru.est0y.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    void insert(String name, long authorId, long genreId);

    void update(long id, String name, long authorId, long genreId);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);
}
