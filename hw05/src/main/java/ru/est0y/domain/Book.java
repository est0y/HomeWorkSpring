package ru.est0y.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Book {
    private final long id;
    private final String name;
    private final Author author;
    private final Genre genre;
}
