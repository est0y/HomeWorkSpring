package ru.est0y.repositories;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {
    @Autowired
    private AuthorDaoJpa authorDaoJpa;
    private static final Author EXPECTED_AUTHOR = new Author(1L, "Author 1");

    @Test
    void findAll() {
        var authors = authorDaoJpa.findAll();
        assertThat(authors).containsOnly(EXPECTED_AUTHOR);
    }
}