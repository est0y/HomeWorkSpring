package ru.est0y.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.est0y.domain.BookComment;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookCommentJpaDao implements BookCommentDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void insert(BookComment entity) {
        em.persist(entity);
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return Optional.ofNullable(em.find(BookComment.class, id));
    }

    @Override
    public void update(BookComment entity) {
        em.merge(entity);

    }

    @Override
    public void delete(BookComment bookComment) {
        em.remove(bookComment);
    }

}

