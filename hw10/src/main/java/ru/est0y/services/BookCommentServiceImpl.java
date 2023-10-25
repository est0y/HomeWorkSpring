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
    public void insert(String text, String bookId) {
        var book = bookService.findById(bookId).orElseThrow();
        bookCommentRepository.insert(new BookComment(text, book));
    }

    @Transactional
    @Override
    public void update(String  id, String text) {
        var comment = findById(id).orElseThrow();
        comment.setText(text);
        bookCommentRepository.save(comment);
    }

    @Override
    public Optional<BookComment> findById(String id) {
        return bookCommentRepository.findById(id);
    }

    @Override
    public List<BookComment> findByBookId(String id) {
       return bookCommentRepository.findByBookId(id);
    }

    @Transactional
    @Override
    public void deleteById(String  id) {
        var comment = findById(id).orElseThrow();
        bookCommentRepository.delete(comment);
    }
}
