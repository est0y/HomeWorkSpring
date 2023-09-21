package ru.est0y.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Book {
    private final long id;

    private final String name;

    private final Author author;

    private final Genre genre;
}
