package ru.est0y.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.est0y.domain.Genre;
import ru.est0y.services.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@WebFluxTest(GenreController.class)
class GenreControllerTest {


    @MockBean
    private GenreService genreService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllGenres() throws Exception {
        var genre1 = new Genre("1", "genre 1");
        var genre2 = new Genre("2", "genre 2");
        given(genreService.findAll()).willReturn(Flux.fromIterable(List.of(genre1, genre2)));
        webTestClient.get().uri("/genre").accept(MediaType.TEXT_EVENT_STREAM).exchange().expectStatus().isOk()
                .expectBodyList(Genre.class).hasSize(2)
                .contains(genre1, genre2);
        verify(genreService).findAll();
    }
}