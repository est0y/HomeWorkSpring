package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.est0y.domain.Book;


public interface BookRepository extends ReactiveMongoRepository<Book,String> {
}
