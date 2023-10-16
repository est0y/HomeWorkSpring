package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.domain.Book;


public interface BookRepository extends MongoRepository<Book,String > {

}
