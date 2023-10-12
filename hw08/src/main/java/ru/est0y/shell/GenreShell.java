package ru.est0y.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.est0y.domain.Genre;
import ru.est0y.services.GenreService;
import ru.est0y.services.stringifiers.Stringifier;

@ShellComponent
@ShellCommandGroup("genres")
@RequiredArgsConstructor
public class GenreShell {
    private final GenreService genreService;

    private final Stringifier<Genre> genreStringifier;

    @ShellMethod(key = {"dag", "display-all-genres"}, value = "display all genres")
    public String displayAll() {
        return genreStringifier.stringify(genreService.findAll());
    }
}
