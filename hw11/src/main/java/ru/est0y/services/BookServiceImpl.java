package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.repositories.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Mono<Void> insert(BookDtoWithoutId bookDto) {

        try {
            var authorMono = authorService.findById(bookDto.getAuthorId());
            var genreMono = genreService.findById(bookDto.getGenreId());
            return Mono.zip(authorMono, genreMono, (author, genre) -> new Book(null, bookDto.getName(), author, genre))
                    .flatMap(bookRepository::insert)
                    .then();
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
            return Mono.error(dataAccessException);
        }
    }

    @Transactional
    @Override
    public Mono<Void> update(BookDto bookDto) {
        var authorMono = authorService.findById(bookDto.getAuthorId());
        var genreMono = genreService.findById(bookDto.getGenreId());
        return Mono.zip(
                        authorMono,
                        genreMono,
                        (author, genre) -> new Book(bookDto.getId(), bookDto.getName(), author, genre))
                .flatMap(bookRepository::save)
                .then();
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }
}
