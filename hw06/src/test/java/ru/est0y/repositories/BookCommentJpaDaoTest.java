package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Book;
import ru.est0y.domain.BookComment;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.est0y.repositories.Config.EXPECTED_BOOK;

@DataJpaTest
@Import(BookCommentJpaDao.class)
class BookCommentJpaDaoTest {
    private static final BookComment EXPECTED_COMMENT1 = new BookComment(1L, "Comment 1", EXPECTED_BOOK);
    private static final BookComment EXPECTED_COMMENT2 = new BookComment(2L, "Comment 2", EXPECTED_BOOK);
    private static final String EXPECTED_TEXT = "test text";
    @Autowired
    private BookCommentJpaDao bookCommentJpaDao;
    @Autowired
    private TestEntityManager em;

    @Test
    void insert() {
        var book = em.find(Book.class, 1);
        bookCommentJpaDao.insert(new BookComment(0, EXPECTED_TEXT, book));
        var comment = em.find(BookComment.class, 3);
        assertThat(comment.getBook()).isEqualTo(book);
        assertThat(comment.getText()).isEqualTo(EXPECTED_TEXT);
    }

    @Test
    void findById() {
        assertThat(bookCommentJpaDao.findById(1).orElseThrow()).isEqualTo(EXPECTED_COMMENT1);
    }

    @Test
    void update() {
        var comment = em.find(BookComment.class, 1);
        comment.setText(EXPECTED_TEXT);
        em.detach(comment);
        bookCommentJpaDao.update(comment);
        assertThat(em.find(BookComment.class, 1).getText()).isEqualTo(EXPECTED_TEXT);
    }

    @Test
    void delete() {
        var comment = em.find(BookComment.class, 1);
        bookCommentJpaDao.delete(comment);
        assertThat(em.find(BookComment.class, 1)).isNull();
    }

    @Test
    void findByBookId() {
        var comments = bookCommentJpaDao.findByBookId(1);
        assertThat(comments).contains(EXPECTED_COMMENT2, EXPECTED_COMMENT1);
    }
}