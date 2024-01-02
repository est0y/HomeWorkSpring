package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.est0y.domain.Genre;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends MongoRepository<Genre, String> {
}
