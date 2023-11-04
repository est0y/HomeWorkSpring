package ru.est0y.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.dto.SimpleBookDto;
import ru.est0y.services.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@WebFluxTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private WebTestClient webTestClient;


    private final List<Author> authors = List.of(new Author("1", "Author1"),
            new Author("2", "Author2"));
    private final List<Genre> genres = List.of(new Genre("1", "Genre1"),
            new Genre("2", "Genre2"));
    private final List<Book> books = List.of(
            new Book("1", "Book1", authors.get(0), genres.get(0)),
            new Book("2", "Book2", authors.get(1), genres.get(1))
    );


    @Test
    void testGetBook() {
        init();
        webTestClient.get().uri("/book/1").accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(Book.class).isEqualTo(books.get(0));
        verify(bookService).findById("1");
    }

    @Test
    void getAllBooks() {
        init();
        var dto1 = new SimpleBookDto(books.get(0));
        var dto2 = new SimpleBookDto(books.get(1));
        webTestClient.get().uri("/book").accept(MediaType.TEXT_EVENT_STREAM).exchange().expectStatus().isOk()
                .expectBodyList(SimpleBookDto.class).hasSize(2)
                .contains(dto1, dto2);
        verify(bookService).findAll();
    }

    @Test
    void testCreateBook() {
        init();
        var dto = new BookDtoWithoutId("name", "2", "2");
        webTestClient.post().uri("/book").bodyValue(dto)
                .exchange().expectStatus().isCreated();
        verify(bookService).insert(dto);
    }

    @Test
    void testUpdateBook() {
        init();
        var dto = new BookDto("1", "name", "2", "2");
        webTestClient.put().uri("/book").bodyValue(dto)
                .exchange().expectStatus().isOk();
        verify(bookService).update(dto);
    }

    @Test
    void testDeleteBook() {
        init();
        webTestClient.delete().uri("/book/1").attribute("id", "1")
                .exchange().expectStatus().isNoContent();
        verify(bookService).deleteById("1");
    }

    private void init() {
        reset(bookService);
        given(bookService.findAll()).willReturn(Flux.fromIterable(books));
        given(bookService.findById("1")).willReturn(Mono.just(books.get(0)));
        given(bookService.findById("2")).willReturn(Mono.just(books.get(1)));
        given(bookService.deleteById("1")).willReturn(Mono.empty());
        given(bookService.update(any())).willReturn(Mono.empty());
        given(bookService.insert(any())).willReturn(Mono.empty());
    }

}