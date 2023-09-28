package ru.est0y.services.stringifiers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;

@Service
@RequiredArgsConstructor
public class BooksStringifier implements Stringifier<Book> {

    private final Stringifier<Author> authorStringifier;

    private final Stringifier<Genre> genreStringifier;

    @Override
    public String stringify(Book book) {
        return String.format("id:%d, name:%s, author:(%s), genre:(%s)",
                book.getId(),
                book.getName(),
                authorStringifier.stringify(book.getAuthor()),
                genreStringifier.stringify(book.getGenre()));
    }
}
