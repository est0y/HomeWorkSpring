package ru.est0y.services;



import ru.est0y.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(String id);

    List<Author>findAll();
}
