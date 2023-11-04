package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.BookComment;


public interface BookCommentRepository extends ReactiveMongoRepository<BookComment, String> {
    Flux<BookComment> findByBookId(String bookId);


    Mono<Void> deleteById(String id);
}
