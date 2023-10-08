package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Book;
import ru.est0y.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void insert(String name, long authorId, long genreId) {
            var author = authorService.findById(authorId).orElseThrow();
            var genre = genreService.findById(genreId).orElseThrow();
            bookRepository.save(new Book(0L, name, author, genre));
    }

    @Transactional
    @Override
    public void update(long id, String name, long authorId, long genreId) {
        var book = bookRepository.findById(id).orElseThrow();
        var author = authorService.findById(authorId).orElseThrow();
        var genre = genreService.findById(genreId).orElseThrow();
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
