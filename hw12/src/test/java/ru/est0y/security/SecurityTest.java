package ru.est0y.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.est0y.controllers.BookController;
import ru.est0y.domain.Book;
import ru.est0y.services.AuthorService;
import ru.est0y.services.BookService;
import ru.est0y.services.GenreService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookController.class)
class SecurityTest {

@Configuration
@Import(BookController.class)
public static class ConfigWithoutMongock{}



    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean

    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @WithMockUser(username = "user")
    @Test
    public void postTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/create").with(csrf())).andExpect(status().isBadRequest());
    }

    @Test
    public void postWithOutAuthTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/create").with(csrf())).andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "user")
    @ParameterizedTest
    @ValueSource(strings = {"/","/books","/creation"})
    public void pagesTest(String url) throws Exception {
        mvc.perform(get(url)).andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/","/books","/creation"})
    public void pagesWithoutAuthTest(String url) throws Exception {
        mvc.perform(get(url)).andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "user")
    @Test
    public void editTest() throws Exception {
        given(bookService.findById("1")).willReturn(Optional.of(new Book("1",null,null,null)));
        mvc.perform(get("/edit").param("id", "1")).andExpect(status().isOk());
    }

    @Test
    public void editWithoutAuthTest() throws Exception {
        given(bookService.findById("1")).willReturn(Optional.of(new Book("1",null,null,null)));
        mvc.perform(get("/edit").param("id", "1")).andExpect(status().isUnauthorized());
    }
    @Test
    public void loginWithoutAuthTest() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }


}