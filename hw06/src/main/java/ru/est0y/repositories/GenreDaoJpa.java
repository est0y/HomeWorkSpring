package ru.est0y.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Genre;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> findAll() {
        return em.createQuery(
                "select g from Genre g"
                , Genre.class).getResultList();
    }

}
