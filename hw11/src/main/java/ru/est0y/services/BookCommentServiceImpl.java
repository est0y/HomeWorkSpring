package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.BookComment;
import ru.est0y.repositories.BookCommentRepository;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;

    private final BookService bookService;

    @Transactional
    @Override
    public Mono<Void> insert(String text, String bookId) {
       return bookService.findById(bookId)
                .doOnNext(book -> bookCommentRepository.insert(new BookComment(text, book))).then();
    }

    @Transactional
    @Override
    public Mono<Void> update(String id, String text) {
        return findById(id).doOnNext(c -> c.setText(text))
                .doOnNext(bookCommentRepository::save).then();
    }

    @Override
    public Mono<BookComment> findById(String id) {
        return bookCommentRepository.findById(id);
    }

    @Override
    public Flux<BookComment> findByBookId(String id) {
        return bookCommentRepository.findByBookId(id);
    }

    @Transactional
    @Override
    public Mono<Void> deleteById(String id) {
        return bookCommentRepository.deleteById(id);
    }
}
