package ru.est0y.repositories;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static ru.est0y.repositories.Config.*;

@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    @Autowired
    private BookDaoJpa bookDaoJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    void deleteById() {
        bookDaoJpa.deleteById(1);
        assertThat(bookDaoJpa.findAll()).isEmpty();
    }

    @Test
    void update() {
        var expectedBook = new Book(1L, "Book 1 updated", new Author(1L, "Author 1"), new Genre(1L, "Genre 1"));
        bookDaoJpa.update(expectedBook);
        optional(Book.class).createAssert(bookDaoJpa.findById(1)).get().isEqualTo(expectedBook);
    }

    @Test
    void insert() {
        var name = "Book 2";
        var author = new Author(1L, "Author 1");
        var genre = new Genre(1L, "Genre 1");
        var book = new Book(0L, name, author, genre);
        var expectedBook = new Book(2L, name, author, genre);
        bookDaoJpa.insert(book);
        optional(Book.class).createAssert(bookDaoJpa.findById(2)).get().isEqualTo(expectedBook);

    }

    @Test
    void findById() {
        var book = bookDaoJpa.findById(1);
        optional(Book.class).createAssert(book).get().isEqualTo(EXPECTED_BOOK);
    }

    @Test
    void findAll() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        var allBooks = bookDaoJpa.findAll();
        assertThat(allBooks).containsOnly(EXPECTED_BOOK);
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1);
    }
}