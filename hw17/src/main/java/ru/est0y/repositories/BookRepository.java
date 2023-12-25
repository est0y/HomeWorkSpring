package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.est0y.domain.Book;

@RepositoryRestResource(path = "book")

public interface BookRepository extends MongoRepository<Book, String> {

}
