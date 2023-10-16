package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
    public void insert(String name, String authorId, String genreId) {
        try {
            var author = authorService.findById(authorId).orElseThrow();
            var genre = genreService.findById(genreId).orElseThrow();
            bookRepository.insert(new Book(null,name,author,genre));
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void update(String id, String name, String authorId, String genreId) {
        var author = authorService.findById(authorId).orElseThrow();
        var genre = genreService.findById(genreId).orElseThrow();
        bookRepository.save(new Book(id,name,author,genre));
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
