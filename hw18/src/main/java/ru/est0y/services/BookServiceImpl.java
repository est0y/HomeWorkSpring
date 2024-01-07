package ru.est0y.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.Genre;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
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
    public void insert(BookDtoWithoutId bookDto) {
        try {
            var author = authorService.findById(bookDto.getAuthorId()).orElseThrow();
            var genre = genreService.findById(bookDto.getGenreId()).orElseThrow();
            bookRepository.insert(new Book(null, bookDto.getName(), author, genre));
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void update(BookDto bookDto) {
        var author = authorService.findById(bookDto.getAuthorId()).orElseThrow();
        var genre = genreService.findById(bookDto.getGenreId()).orElseThrow();
        bookRepository.save(new Book(bookDto.getId(), bookDto.getName(), author, genre));
    }

    @HystrixCommand(fallbackMethod = "getEmptyBook")
    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }


    @HystrixCommand(fallbackMethod = "getEmptyBooks")
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    private Optional<Book> getEmptyBook(String id) {
        return Optional.of(new Book(id, "N/A", new Author("0", "N/A"),
                new Genre("0", "N/A")));
    }

    private List<Book> getEmptyBooks() {
        var book = getEmptyBook("0").orElseThrow();
        return List.of(book);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
