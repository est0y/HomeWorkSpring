package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.est0y.domain.Genre;
import ru.est0y.services.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(path = "/genre", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Genre> getAllGenres() {
        return genreService.findAll();
    }

}
