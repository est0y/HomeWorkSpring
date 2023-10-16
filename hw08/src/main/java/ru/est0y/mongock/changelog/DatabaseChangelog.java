package ru.est0y.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.BookComment;
import ru.est0y.domain.Genre;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {
    private final Author author1 = new Author("1", "Артур Конан Дойл");

    private final Author author2 = new Author("2", "Джоан Роулинг");

    private final Genre genre1 = new Genre("1", "Фантастика");

    private final Genre genre2 = new Genre("2", "Детектив");

    private final Book book1 = new Book("1", "Гарри Поттер", author1, genre1);

    private final Book book2 = new Book("2", "Шерлок Хомс", author2, genre2);

    private final BookComment bookComment1 = new BookComment("1","Отличная", book1);

    private final BookComment bookComment2 = new BookComment("2","Очень хорошая", book2);

    private final BookComment bookComment3 = new BookComment("3","Супер", book2);

    @ChangeSet(order = "001", id = "dropDb", author = "est0y", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "est0y")
    public void insertAuthors(MongockTemplate mongockTemplate) {
        mongockTemplate.insertAll(List.of(author1, author2));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "est0y")
    public void insertGenres(MongockTemplate mongockTemplate) {
        mongockTemplate.insertAll(List.of(genre1, genre2));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "est0y")
    public void insertBooks(MongockTemplate mongockTemplate) {
        mongockTemplate.insertAll(List.of(book1, book2));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "est0y")
    public void insertComments(MongockTemplate mongockTemplate) {
        mongockTemplate.insertAll(List.of(bookComment1, bookComment2,bookComment3));
    }
}