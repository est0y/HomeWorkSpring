package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Author;
import ru.est0y.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;
    private static final Genre EXPECTED_GENRE = new Genre(1L, "Genre 1");

    @Test
    void findAll() {
        var genres = genreDaoJdbc.findAll();
        assertThat(genres).containsOnly(EXPECTED_GENRE);
    }
}