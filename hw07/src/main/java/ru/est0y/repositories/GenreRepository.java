package ru.est0y.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.Genre;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre,Long> {
    List<Genre> findAll();
}
