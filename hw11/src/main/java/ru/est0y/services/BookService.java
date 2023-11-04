package ru.est0y.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;


public interface BookService {

    Mono<Void> insert(BookDtoWithoutId bookDto);

    Mono<Void> update(BookDto bookDto);

    Mono<Book> findById(String id);

    Flux<Book> findAll();

    Mono<Void> deleteById(String id);
}
