package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ru.est0y.domain.jpa.Author;
import ru.est0y.domain.jpa.Book;
import ru.est0y.domain.jpa.Genre;
import ru.est0y.domain.mongo.AuthorDocument;
import ru.est0y.domain.mongo.BookDocument;
import ru.est0y.domain.mongo.GenreDocument;

@Service
@RequiredArgsConstructor
public class BookConverter implements Converter<ru.est0y.domain.jpa.Book, BookDocument> {

    private final Converter<Author, AuthorDocument> authorConverter;

    private final Converter<Genre, GenreDocument> genreConverter;


    @Override
    public BookDocument convert(Book book) {
        var genre = book.getGenre();
        var author = book.getAuthor();
        return new BookDocument(String.valueOf(book.getId()),
                book.getName(),
                authorConverter.convert(author),
                genreConverter.convert(genre)
        );
    }
}
