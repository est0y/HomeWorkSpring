package ru.est0y.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.est0y.domain.Book;
import ru.est0y.exceptions.UpdateFailException;
import ru.est0y.services.BookServiceImpl;
import ru.est0y.services.stringifiers.Stringifier;

@ShellComponent
@ShellCommandGroup("books")
@RequiredArgsConstructor
public class BooksShell {

    private final Stringifier<Book> bookStringifier;

    private final BookServiceImpl bookService;

    @ShellMethod(key = {"dab", "display-all-books"}, value = "display all books")
    public String displayAll() {
        return bookStringifier.stringify(bookService.findAll());
    }

    @ShellMethod(key = {"dbi", "display-by-id"}, value = "display book by id")
    public String displayById(@ShellOption(value = "id") String bookId) {
        var optionalBook = bookService.findById(bookId);
        if (optionalBook.isEmpty()) {
            return "Not found";
        }
        return bookStringifier.stringify(optionalBook.get());
    }

    @ShellMethod(key = {"ab", "insert-book"}, value = "add book")
    public void insertBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String genreId) {
        bookService.insert(name, authorId, genreId);
    }

    @ShellMethod(key = {"ub", "update-book"}, value = "update book")
    public String updateBook(@ShellOption(value = "book-id") String bookId,
                             @ShellOption(value = "name") String name,
                             @ShellOption(value = "author-id") String authorId,
                             @ShellOption(value = "genre-id") String genreId) {
        try {
            bookService.update(bookId, name, authorId, genreId);
            return "";
        } catch (UpdateFailException e) {
            return "Update Fail";
        }

    }

    @ShellMethod(key = {"dbbi", "delete-book-by-id"}, value = "delete book by id")
    public void deleteBook(@ShellOption String bookId) {
        bookService.deleteById(bookId);
    }
}
