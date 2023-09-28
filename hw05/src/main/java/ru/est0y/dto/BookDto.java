package ru.est0y.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private long id;

    private String name;

    private long authorId;

    private long genreId;
}
