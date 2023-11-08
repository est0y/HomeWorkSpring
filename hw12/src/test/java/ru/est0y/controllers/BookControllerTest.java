package ru.est0y.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.services.AuthorService;
import ru.est0y.services.BookService;
import ru.est0y.services.GenreService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookService bookService;

    @BeforeEach
    private void init() {
        reset(authorService);
        reset(genreService);
        reset(bookService);
        var authors = List.of(new Author("1", "Author1"), new Author("2", "Author2"));
        var genres = List.of(new Genre("1", "Genre1"), new Genre("2", "Genre2"));
        var books = List.of(new Book("1", "Book1", authors.get(0), genres.get(0)));
        given(authorService.findAll()).willReturn(authors);
        given(genreService.findAll()).willReturn(genres);
        given(bookService.findAll()).willReturn(books);
        given(bookService.findById("1")).willReturn(Optional.ofNullable(books.get(0)));
    }

    
    @Test
    void getBooks() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get("/books")).andExpect(status().isOk());
    }

    
    @Test
    void editBook() throws Exception {
        mvc.perform(get("/edit").param("id", "1")).andExpect(status().isOk());
    }

    
    @Test
    void updateBook() throws Exception {
        mvc.perform(post("/update")
                        .param("id", "1")
                        .param("name", "name")
                        .param("authorId", "1")
                        .param("genreId", "1"))
                .andExpect(status().is3xxRedirection());
    }

    
    @Test
    void updateBookNotValidParams() throws Exception {
        mvc.perform(post("/update")
                        .param("id", "1"))
                .andExpect(status().is4xxClientError());
    }

    
    @Test
    void creationBook() throws Exception {
        mvc.perform(get("/creation")).andExpect(status().isOk());
    }

    
    @Test
    void createBook() throws Exception {
        mvc.perform(post("/create")
                        .param("id", "1")
                        .param("name", "name")
                        .param("authorId", "1")
                        .param("genreId", "1"))
                .andExpect(status().is3xxRedirection());
    }

    
    @Test
    void createBookNotValidParams() throws Exception {
        mvc.perform(post("/create")
                        .param("genreId", "1"))
                .andExpect(status().is4xxClientError());
    }

    
    @Test
    void deleteBook() throws Exception {
        mvc.perform(post("/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection());
    }
}