package ru.est0y.repositories;

import ru.est0y.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao {
    void insert(BookComment entity);

    Optional<BookComment> findById(long id);

    void update(BookComment entity);

    void delete(BookComment bookComment);

    List<BookComment> findByBookId(long bookId);
}
