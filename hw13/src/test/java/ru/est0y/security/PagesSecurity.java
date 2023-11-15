package ru.est0y.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.est0y.controllers.PagesController;
import ru.est0y.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = PagesController.class)
@Import({SecurityConfiguration.class,PagesController.class})
public class PagesSecurity {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = {"EDITOR"})
    @ParameterizedTest
    @ValueSource(strings = {"/", "/books", "/creation","/edit/1"})
    public void pagesAccessibleToEditorTest(String url) throws Exception {
        mvc.perform(get(url)).andExpect(status().isOk());
    }

    @WithMockUser(roles = {"CLIENT"})
    @ParameterizedTest
    @ValueSource(strings = {"/", "/books"})
    public void pagesAccessibleToClientTest(String url) throws Exception {
        mvc.perform(get(url)).andExpect(status().isOk());
    }

    @WithMockUser(roles = {"CLIENT"})
    @ParameterizedTest
    @ValueSource(strings = {"/creation","/edit/1"})
    public void PagesAreNotAccessibleToClient(String url) throws Exception {
        mvc.perform(get(url)).andExpect(status().isForbidden());
    }

    @Test
    public void loginWithoutAuthTest() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }
}
