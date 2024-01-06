package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.est0y.domain.Author;

@RepositoryRestResource(path = "author")
public interface AuthorRepository extends MongoRepository<Author, String> {
}
