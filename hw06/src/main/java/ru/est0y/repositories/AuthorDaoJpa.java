package ru.est0y.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Author;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDaoJpa implements AuthorDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Author> findAll() {
        return em.createQuery(
                "select a from Author a"
                , Author.class).getResultList();
    }
}
