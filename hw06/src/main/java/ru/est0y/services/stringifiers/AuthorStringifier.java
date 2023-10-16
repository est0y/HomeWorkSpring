package ru.est0y.services.stringifiers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorStringifier implements Stringifier<Author> {

    @Override
    public String stringify(Author author) {
        return String.format("id:%d, name:%s", author.getId(), author.getName());
    }
}
