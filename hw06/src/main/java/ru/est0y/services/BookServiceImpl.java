package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Book;
import ru.est0y.repositories.BookDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookDao bookDao;

    @Transactional
    @Override
    public void insert(String name, long authorId, long genreId) {
            var author = authorService.findById(authorId).orElseThrow();
            var genre = genreService.findById(genreId).orElseThrow();
            bookDao.insert(new Book(0L,name,author,genre));
    }

    @Transactional
    @Override
    public void update(long id, String name, long authorId, long genreId) {
        var book = bookDao.findById(id).orElseThrow();
        var author = authorService.findById(authorId).orElseThrow();
        var genre = genreService.findById(genreId).orElseThrow();
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);
        bookDao.update(new Book(id,name,author,genre));
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
