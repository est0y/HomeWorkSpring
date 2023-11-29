package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookRepository bookRepository;

    @Secured("ROLE_EDITOR")
    @Transactional
    @Override
    public void insert(BookDtoWithoutId bookDto) {
        try {
            var author = authorService.findById(bookDto.getAuthorId()).orElseThrow();
            var genre = genreService.findById(bookDto.getGenreId()).orElseThrow();
            bookRepository.insert(new Book(null,bookDto.getName(),author,genre));
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
    }

    @Secured("ROLE_EDITOR")
    @Transactional
    @Override
    public void update(BookDto bookDto) {
        var author = authorService.findById(bookDto.getAuthorId()).orElseThrow();
        var genre = genreService.findById(bookDto.getGenreId()).orElseThrow();
        bookRepository.save(new Book(bookDto.getId(),bookDto.getName(),author,genre));
    }

    @Secured({"ROLE_CLIENT","ROLE_EDITOR"})
    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Secured({"ROLE_CLIENT","ROLE_EDITOR"})
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Secured("ROLE_EDITOR")
    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
