package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.est0y.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author,String > {

}
