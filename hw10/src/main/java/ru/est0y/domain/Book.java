package ru.est0y.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Document("books")
public class Book {
    @Id
    private final String id;

    private final String name;

    @DBRef
    private final Author author;

    private final Genre genre;
}
