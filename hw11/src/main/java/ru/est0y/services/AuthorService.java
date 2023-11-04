package ru.est0y.services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Author;

public interface AuthorService {
    Mono<Author> findById(String id);

    Flux<Author> findAll();
}
