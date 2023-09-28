package ru.est0y.services.stringifiers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreStringifier implements Stringifier<Genre> {

    @Override
    public String stringify(Genre genre) {
        return String.format("id:%d, name:%s", genre.getId(), genre.getName());
    }
}
