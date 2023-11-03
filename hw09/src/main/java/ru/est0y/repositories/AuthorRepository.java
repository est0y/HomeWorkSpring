package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.domain.Author;


public interface AuthorRepository extends MongoRepository<Author,String > {

}
