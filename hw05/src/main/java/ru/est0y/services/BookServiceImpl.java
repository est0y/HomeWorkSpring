package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.repositories.BookDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public void insert(String name, long authorId, long genreId) {
        try {
            bookDao.insert(new BookDto(0L, name, authorId, genreId));
        } catch (DataAccessException dataAccessException) {
            dataAccessException.printStackTrace();
        }
    }

    @Override
    public void update(long id, String name, long authorId, long genreId) {
        bookDao.update(new BookDto(id, name, authorId, genreId));

    }

    @Override
    public Optional<Book> findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
