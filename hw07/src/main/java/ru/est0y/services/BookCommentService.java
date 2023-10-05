package ru.est0y.services;

import ru.est0y.domain.BookComment;

import java.util.List;
import java.util.Optional;


public interface BookCommentService {

    void insert(String text, long bookId);

    void update(long id, String text);

    Optional<BookComment> findById(long id);

    List<BookComment>findByBookId(long id);

    void deleteById(long id);
}
