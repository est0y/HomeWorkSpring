package ru.est0y.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDtoWithoutId {
    @NotBlank
    private final String name;

    @NotBlank
    private final String authorId;

    @NotBlank
    private final String genreId;
}
