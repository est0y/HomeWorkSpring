package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {
    @Autowired
    private GenreDaoJpa genreDaoJpa;
    private static final Genre EXPECTED_GENRE = new Genre(1L, "Genre 1");

    @Test
    void findAll() {
        var genres = genreDaoJpa.findAll();
        assertThat(genres).containsOnly(EXPECTED_GENRE);
    }
}