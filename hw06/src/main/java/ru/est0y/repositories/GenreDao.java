package ru.est0y.repositories;


import ru.est0y.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre>findById(long id);

    List<Genre> findAll();
}
