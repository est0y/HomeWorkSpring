package ru.est0y.services;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.est0y.domain.jpa.Author;
import ru.est0y.domain.mongo.AuthorDocument;

@Service
public class AuthorConverter implements Converter<Author, AuthorDocument> {
    @Override
    public AuthorDocument convert(Author author) {
        return new AuthorDocument(String.valueOf(author.getId()),author.getName());
    }
}
