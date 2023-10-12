package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.est0y.Config.*;
@DataMongoTest
class BookCommentRepositoryTest {

    @Autowired
    private BookCommentRepository bookCommentRepository;

    @Test
    void findByBookId() {
        assertThat(bookCommentRepository.findByBookId("1")).containsOnly(bookComment1,bookComment2);
    }
}