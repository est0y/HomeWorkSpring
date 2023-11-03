package ru.est0y.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Genre;

public interface GenreService {

    Mono<Genre> findById(String id);

    Flux<Genre> findAll();
}
