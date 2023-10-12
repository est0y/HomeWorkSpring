package ru.est0y.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.est0y.domain.BookComment;

import java.util.List;


public interface BookCommentRepository extends MongoRepository<BookComment,String > {
    List<BookComment>findByBookId(String bookId);
}
