package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Author;
import ru.est0y.repositories.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Mono<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }
}
