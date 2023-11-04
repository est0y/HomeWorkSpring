package ru.est0y.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.dto.SimpleBookDto;
import ru.est0y.services.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    public Mono<Book> getBook(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @GetMapping(path = "/book", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SimpleBookDto> getAllBooks() {
        return bookService.findAll().map(SimpleBookDto::new);
    }


    @PutMapping("/book")
    public Mono<ResponseEntity<Void>> updateBook(@Valid @RequestBody BookDto bookDto) {
        return bookService.update(bookDto)
                .then(Mono.just(ResponseEntity.ok().build()));
    }


    @PostMapping("/book")
    public Mono<ResponseEntity<Void>> createBook(@Valid @RequestBody BookDtoWithoutId bookDto) {
        return bookService.insert(bookDto)
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @DeleteMapping("/book/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
        return bookService.deleteById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
