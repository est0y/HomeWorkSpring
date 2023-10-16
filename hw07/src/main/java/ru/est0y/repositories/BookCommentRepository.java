package ru.est0y.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.est0y.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends CrudRepository<BookComment,Long> {
    List<BookComment> findCommentsByBookId(long bookId);
}
