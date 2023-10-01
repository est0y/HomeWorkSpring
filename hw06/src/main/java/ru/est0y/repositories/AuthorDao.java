package ru.est0y.repositories;

import ru.est0y.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Optional<Author>findById(long id);
    
    List<Author>findAll();
}
