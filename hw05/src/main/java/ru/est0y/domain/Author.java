package ru.est0y.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Author {
    private final long id;
    private final String firstName;
    private final String secondName;
}
