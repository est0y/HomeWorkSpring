package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;
    private static final Author EXPECTED_AUTHOR = new Author(1L, "Author 1");

    @Test
    void findAll() {
        var authors = authorDaoJdbc.findAll();
        assertThat(authors).containsOnly(EXPECTED_AUTHOR);
    }
}