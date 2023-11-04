package ru.est0y.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.BookComment;


public interface BookCommentService {

     Mono<Void> insert(String text, String bookId);

     Mono<Void> update(String  id, String text);

    Mono<BookComment> findById(String id);

    Flux<BookComment> findByBookId(String id);

     Mono<Void> deleteById(String id);
}
