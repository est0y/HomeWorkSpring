package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.BookComment;
import ru.est0y.repositories.BookCommentDao;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentDao bookCommentDao;

    private final BookService bookService;

    @Transactional
    @Override
    public void insert(String text, long bookId) {
        var book = bookService.findById(bookId).orElseThrow();
        bookCommentDao.insert(new BookComment(0L, text, book));
    }

    @Transactional
    @Override
    public void update(long id, String text) {
        var comment = findById(id).orElseThrow();
        comment.setText(text);
        bookCommentDao.update(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookComment> findById(long id) {
        return bookCommentDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookComment> findByBookId(long id) {
        return bookCommentDao.findByBookId(id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        var comment = findById(id).orElseThrow();
        bookCommentDao.delete(comment);
    }
}
