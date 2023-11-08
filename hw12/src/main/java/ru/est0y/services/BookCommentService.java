package ru.est0y.services;

import ru.est0y.domain.BookComment;

import java.util.List;
import java.util.Optional;


public interface BookCommentService {

    void insert(String text, String bookId);

    void update(String  id, String text);

    Optional<BookComment> findById(String id);

    List<BookComment>findByBookId(String id);

    void deleteById(String id);
}
