package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.est0y.domain.Genre;
import ru.est0y.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return new ResponseEntity<>(genreService.findAll(),HttpStatus.OK);
    }

}
