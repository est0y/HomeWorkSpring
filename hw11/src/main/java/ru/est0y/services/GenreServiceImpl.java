package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Genre;
import ru.est0y.repositories.GenreRepository;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public Mono<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public Flux<Genre> findAll() {
        return genreRepository.findAll();
    }
}
