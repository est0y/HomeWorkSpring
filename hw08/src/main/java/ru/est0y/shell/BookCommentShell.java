package ru.est0y.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.est0y.services.BookCommentService;
import ru.est0y.services.stringifiers.BookCommentStringifier;

@ShellComponent
@ShellCommandGroup("book comments")
@RequiredArgsConstructor
public class BookCommentShell {
    private final BookCommentService bookCommentService;

    private final BookCommentStringifier bookCommentStringifier;

    @ShellMethod(key = {"dcbbi", "display-comments-by-book-id"}, value = "display comments by book id")
    public String displayByBookId(@ShellOption(value = "bookId") String bookId) {
        return bookCommentStringifier.stringify(
                bookCommentService.findByBookId(bookId)
        );
    }

    @ShellMethod(key = {"dcbi", "display-comment-by-id"}, value = "display comment by id")
    public String displayById(@ShellOption(value = "id") String id) {
        var optionalComment = bookCommentService.findById(id);
        if (optionalComment.isEmpty()) {
            return "Comment Not Found";
        } else {
            return bookCommentStringifier.stringify(optionalComment.get());
        }
    }

    @ShellMethod(key = {"ic", "insert-comment"}, value = "insert comment")
    public void insertComment(@ShellOption(value = "text") String text, @ShellOption(value = "bookId") String bookId) {
        bookCommentService.insert(text, bookId);
    }

    @ShellMethod(key = {"uc", "update-comment"}, value = "update comment")
    public void updateComment(@ShellOption(value = "id") String id, @ShellOption(value = "text") String text) {
        bookCommentService.update(id, text);
    }

    @ShellMethod(key = {"dc"}, value = "delete comment")
    public void deleteComment(@ShellOption(value = "id") String id) {
        bookCommentService.deleteById(id);
    }
}
