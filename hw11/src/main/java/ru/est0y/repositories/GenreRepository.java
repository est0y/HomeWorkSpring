package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.est0y.domain.Genre;


public interface GenreRepository extends ReactiveMongoRepository<Genre,String > {

}
