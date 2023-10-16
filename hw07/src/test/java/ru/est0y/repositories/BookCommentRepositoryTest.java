package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.est0y.domain.BookComment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookCommentRepositoryTest {
    @Autowired
    private BookCommentRepository bookCommentRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findCommentsByBookId() {
        var comment1 = em.find(BookComment.class, 1L);
        var comment2 = em.find(BookComment.class, 2L);
        assertThat(bookCommentRepository.findCommentsByBookId(1)).containsOnly(comment1, comment2);
    }
}