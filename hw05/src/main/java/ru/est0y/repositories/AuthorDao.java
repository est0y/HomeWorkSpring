package ru.est0y.repositories;

import ru.est0y.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author>findAll();
}
