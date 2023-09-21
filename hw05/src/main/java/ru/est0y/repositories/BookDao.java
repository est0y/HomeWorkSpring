package ru.est0y.repositories;

import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void insert(BookDto entity);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void update(BookDto entity);

    void deleteById(long id);


}
