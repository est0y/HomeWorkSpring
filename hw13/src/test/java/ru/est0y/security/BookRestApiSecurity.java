package ru.est0y.security;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.method.MethodSecurityBeanDefinitionParser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.est0y.controllers.BookController;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.services.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Disabled
@WebMvcTest(value = BookController.class)
@Import({MethodSecurityBeanDefinitionParser.SecuredAuthorizationMethodInterceptor.class, SecurityConfiguration.class, BookController.class, BookServiceImpl.class})
class BookRestApiSecurity {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private UserService userService;


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

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/book/1")).andExpect(status().isOk());
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/book")).andExpect(status().isOk());
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorPostTest() throws Exception {
        mvc.perform(post("/book")).andExpect(status().isBadRequest());

    }

    @WithMockUser(roles = "CLIENT")
    @Test
    public void clientPostTest() throws Exception {
        mvc.perform(post("/book")).andExpect(status().isForbidden());
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorPutTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/book")).andExpect(status().isBadRequest());
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void clientPutTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/book")).andExpect(status().isForbidden());
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorDeleteTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/book/1")).andExpect(status().isOk());
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void clientDeleteTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/book/1")).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/book", "/book/1"})
    public void withoutAuth(String url) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().is3xxRedirection());
        mvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().is3xxRedirection());
        mvc.perform(MockMvcRequestBuilders.put(url)).andExpect(status().is3xxRedirection());
        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = {"ANYROLE"})
    @ParameterizedTest
    @ValueSource(strings = {"/book", "/book/1"})
    public void withoutRequiredRole(String url) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isForbidden());
        mvc.perform(MockMvcRequestBuilders.post(url)).andExpect(status().isForbidden());
        mvc.perform(MockMvcRequestBuilders.put(url)).andExpect(status().isForbidden());
        mvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(status().isForbidden());
    }
}