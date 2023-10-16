package ru.est0y.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {
    @EntityGraph(value = "books-entity-graph")
    List<Book> findAll();
}
