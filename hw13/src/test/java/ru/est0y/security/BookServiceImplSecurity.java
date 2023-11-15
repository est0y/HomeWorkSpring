package ru.est0y.security;


import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import ru.est0y.repositories.BookRepository;
import ru.est0y.services.AuthorService;
import ru.est0y.services.BookServiceImpl;
import ru.est0y.services.GenreService;
import ru.est0y.services.UserService;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Import({SecurityConfiguration.class, BookServiceImpl.class})
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
class BookServiceImplSecurity {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;


    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private UserService userService;

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void getByIdTest() {
        anythingExceptAccessDeniedException(() -> bookService.findById(null));
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void getAllTest() {
        anythingExceptAccessDeniedException(() -> bookService.findAll());
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorPostTest() {
        anythingExceptAccessDeniedException(() -> bookService.insert(null));
    }

    @WithMockUser(roles = "CLIENT")
    @Test
    public void clientPostTest() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.insert(null));
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorPutTest() {
        anythingExceptAccessDeniedException(() -> bookService.update(null));
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void clientPutTest() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.update(null));
    }

    @WithMockUser(roles = {"EDITOR"})
    @Test
    public void editorDeleteTest() {
        anythingExceptAccessDeniedException(() -> bookService.deleteById(null));
    }

    @WithMockUser(roles = {"CLIENT"})
    @Test
    public void clientDeleteTest() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.deleteById(null));
    }

    @Test
    public void withoutAuth() {
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> bookService.deleteById(null));
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> bookService.update(null));
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> bookService.insert(null));
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> bookService.findById(null));
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> bookService.findAll());

    }

    @WithMockUser(roles = {"ANYROLE"})
    @Test
    public void withoutRequiredRole() {
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.deleteById(null));
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.update(null));
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.insert(null));
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.findById(null));
        Assertions.assertThrows(AccessDeniedException.class, () -> bookService.findAll());
    }

    private void anythingExceptAccessDeniedException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            assertNotEquals(AccessDeniedException.class, e.getClass());
        }
    }
}