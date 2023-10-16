package ru.est0y.repositories;


import ru.est0y.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void insert(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(long id);


}
