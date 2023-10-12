package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.domain.Genre;


public interface GenreRepository extends MongoRepository<Genre,String > {

}
