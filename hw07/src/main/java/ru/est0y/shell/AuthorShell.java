package ru.est0y.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.est0y.domain.Author;
import ru.est0y.services.AuthorService;
import ru.est0y.services.stringifiers.Stringifier;

@ShellComponent
@ShellCommandGroup("authors")
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;

    private final Stringifier<Author> authorStringifier;

    @ShellMethod(key = {"daa", "display-all-authors"}, value = "display all authors")
    public String displayAll() {
        return authorStringifier.stringify(authorService.findAll());
    }
}
