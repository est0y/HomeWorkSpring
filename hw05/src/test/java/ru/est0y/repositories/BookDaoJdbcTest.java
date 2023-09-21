package ru.est0y.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.dto.BookDto;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;
import static ru.est0y.repositories.Config.*;

@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    void deleteById() {
        bookDaoJdbc.deleteById(1);
        assertThat(bookDaoJdbc.findAll()).isEmpty();
    }

    @Test
    void update() {
        var expectedBook=new Book(1L,"Book 1 updated",new Author(1L,"Author 1"),new Genre(1L,"Genre 1"));
        bookDaoJdbc.update(new BookDto(1L,"Book 1 updated",1,1));
        optional(Book.class).createAssert(bookDaoJdbc.findById(1)).get().isEqualTo(expectedBook);
    }

    @Test
    void insert(){
        var expectedBook=new Book(2L,"Book 2",new Author(1L,"Author 1"),new Genre(1L,"Genre 1"));
        bookDaoJdbc.insert(new BookDto(0L,"Book 2",1,1));
        optional(Book.class).createAssert(bookDaoJdbc.findById(2)).get().isEqualTo(expectedBook);

    }

    @Test
    void findById() {
        var book = bookDaoJdbc.findById(1);
        optional(Book.class).createAssert(book).get().isEqualTo(EXPECTED_BOOK);
    }

    @Test
    void findAll() {
        var allBooks = bookDaoJdbc.findAll();
        assertThat(allBooks).containsOnly(EXPECTED_BOOK);
    }
}