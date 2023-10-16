package ru.est0y.services.stringifiers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Book;
import ru.est0y.domain.BookComment;

@Service
@RequiredArgsConstructor
public class BookCommentStringifier implements Stringifier<BookComment> {

    private final Stringifier<Book> bookStringifier;

    @Override
    public String stringify(BookComment comment) {
        return String.format("id:%d, text:%s,book:(%s)",
                comment.getId(),
                comment.getText(),
                bookStringifier.stringify(comment.getBook()));
    }
}
