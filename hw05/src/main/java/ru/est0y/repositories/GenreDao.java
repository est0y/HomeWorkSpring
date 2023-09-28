package ru.est0y.repositories;

import ru.est0y.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> findAll();
}
