package ru.est0y.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.est0y.domain.Author;
import ru.est0y.services.AuthorService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@WebFluxTest(AuthorController.class)
class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAllAuthors() throws Exception {
        var author1 = new Author("1", "Author 1");
        var author2 = new Author("2", "Author 2");
        given(authorService.findAll()).willReturn(Flux.fromIterable(List.of(author1, author2)));
        webTestClient.get().uri("/author").accept(MediaType.TEXT_EVENT_STREAM).exchange().expectStatus().isOk()
                .expectBodyList(Author.class).hasSize(2)
                .contains(author1, author2);
        verify(authorService).findAll();
    }
}