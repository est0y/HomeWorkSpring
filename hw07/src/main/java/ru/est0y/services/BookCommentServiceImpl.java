package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.est0y.domain.BookComment;
import ru.est0y.repositories.BookCommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository bookCommentRepository;

    private final BookService bookService;

    @Transactional
    @Override
    public void insert(String text, long bookId) {
        var book = bookService.findById(bookId).orElseThrow();
        bookCommentRepository.save(new BookComment(0L, text, book));
    }

    @Transactional
    @Override
    public void update(long id, String text) {
        var comment = findById(id).orElseThrow();
        comment.setText(text);
        bookCommentRepository.save(comment);
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return bookCommentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookComment> findByBookId(long id) {
        return bookCommentRepository.findCommentsByBookId(id);
    }

    @Override
    public void deleteById(long id) {
        bookCommentRepository.deleteById(id);
    }
}
