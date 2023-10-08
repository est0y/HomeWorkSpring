package ru.est0y.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    List<Author>findAll();
}
