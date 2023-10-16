package ru.est0y.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;

import java.util.List;

import static ru.est0y.Config.*;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "est0y", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "est0y")
    public void insertAuthors(MongockTemplate mongockTemplate) {
        mongockTemplate.insert(author1);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "est0y")
    public void insertGenres(MongockTemplate mongockTemplate) {
        mongockTemplate.insert(genre1);
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