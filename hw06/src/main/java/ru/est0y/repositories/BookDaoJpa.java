package ru.est0y.repositories;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.dto.BookDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public void deleteById(long id) {
        var book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public void update(BookDto bookDto) {
        var authorId = bookDto.getAuthorId();
        var genreId = bookDto.getGenreId();
        var author = em.find(Author.class, authorId);
        var genre = em.find(Genre.class, genreId);
        var book = new Book(bookDto.getId(), bookDto.getName(), author, genre);
        em.merge(book);
    }

    @Override
    public void insert(BookDto bookDto) {
        var authorId = bookDto.getAuthorId();
        var genreId = bookDto.getGenreId();
        var author = em.find(Author.class, authorId);
        var genre = em.find(Genre.class, genreId);
        em.persist(new Book(0L, bookDto.getName(), author, genre));
    }


    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("books-entity-graph");
        var query = em.createQuery(
                "select b from Book b"
                , Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }
}