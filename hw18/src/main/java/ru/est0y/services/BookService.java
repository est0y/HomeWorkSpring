package ru.est0y.services;

import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;

import java.util.List;
import java.util.Optional;


public interface BookService {

    void insert(BookDtoWithoutId bookDto);

    void update(BookDto bookDto);

    Optional<Book> findById(String id);

    List<Book> findAll();

    void deleteById(String  id);
}
