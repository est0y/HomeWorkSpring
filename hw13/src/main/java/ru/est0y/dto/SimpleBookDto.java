package ru.est0y.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.est0y.domain.Book;



@Data
public class SimpleBookDto {
    @NotBlank
    private final String id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String authorName;

    @NotBlank
    private final String genreName;

    public SimpleBookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthor().getName();
        this.genreName = book.getGenre().getName();
    }
}
