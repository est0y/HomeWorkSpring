package ru.est0y.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.est0y.domain.BookComment;

import java.util.List;
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

    public List<BookComment> findByBookId(long bookId) {
        TypedQuery<BookComment> query = em.createQuery(
                "select c from BookComment c where c.book.id=:bookId",
                BookComment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }
}

