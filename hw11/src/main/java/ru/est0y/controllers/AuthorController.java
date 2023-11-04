package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.est0y.domain.Author;
import ru.est0y.services.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(path = "/author", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Author> getAllAuthors() {
        return authorService.findAll();
    }

}
