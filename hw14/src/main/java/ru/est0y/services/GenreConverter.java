package ru.est0y.services;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.est0y.domain.jpa.Genre;
import ru.est0y.domain.mongo.GenreDocument;

@Service
public class GenreConverter implements Converter<Genre, GenreDocument> {
    @Override
    public GenreDocument convert(Genre genre) {
        return new GenreDocument(String.valueOf(genre.getId()), genre.getName());
    }
}
