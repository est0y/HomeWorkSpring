package ru.est0y.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.internal.stubbing.answers.Returns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doAnswer;

@SpringBootTest
class HystrixBookServiceImplTest {

    @MockBean
    private BookCommentService bookCommentService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;


    @Test
    void findById() throws InterruptedException {
        doAnswer(new AnswersWithDelay(10000,
                new Returns(Optional.of(new Book(null, null, null, null)))))
                .when(bookRepository).findById("1");
        var emptyBook = bookService.findById("1").orElseThrow();
        Assertions.assertEquals(new Book("1", "N/A", new Author("0", "N/A"), new Genre("0", "N/A")), emptyBook);
    }

    @Test
    void findAll() {
        doAnswer(new AnswersWithDelay(10000,
                new Returns(List.of(new Book(null, null, null, null)))))
                .when(bookRepository).findAll();
        var emptyBooks = bookService.findAll();
        Assertions.assertEquals(1, emptyBooks.size());
        var emptyBook = emptyBooks.get(0);
        Assertions.assertEquals(new Book("0", "N/A", new Author("0", "N/A"), new Genre("0", "N/A")), emptyBook);
    }
}