package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.est0y.domain.BookComment;

import java.util.List;

@RepositoryRestResource(path = "bookComment")
public interface BookCommentRepository extends MongoRepository<BookComment, String> {
    List<BookComment> findByBookId(String bookId);
}
