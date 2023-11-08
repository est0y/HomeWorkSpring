package ru.est0y.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookDto {
    @NotBlank
    private final String id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String authorId;

    @NotBlank

    private final String genreId;
}
