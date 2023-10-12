package ru.est0y;

import ru.est0y.domain.Author;
import ru.est0y.domain.Book;
import ru.est0y.domain.BookComment;
import ru.est0y.domain.Genre;

public class Config {
    public static final Author author1 = new Author("1", "Артур Конан Дойл");

    public static final Genre genre1 = new Genre("1", "Фантастика");

    public static final Book book1 = new Book("1", "Гарри Поттер", author1, genre1);

    public static final Book book2 = new Book("2", "Шерлок Хомс", author1, genre1);

    public static final BookComment bookComment1 = new BookComment("1","Отличная", book1);
    public static final BookComment bookComment2 = new BookComment("2","Супер", book1);

    public static final BookComment bookComment3 = new BookComment("3","Очень хорошая", book2);
}
