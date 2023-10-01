package ru.est0y.services;

import ru.est0y.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(long id);

    List<Genre>findAll();
}
