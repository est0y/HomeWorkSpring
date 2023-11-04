package ru.est0y.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.est0y.domain.Book;

@Data
@NoArgsConstructor
public class SimpleBookDto {
    @NotBlank
    private  String id;

    @NotBlank
    private  String name;

    @NotBlank
    private  String authorName;

    @NotBlank
    private  String genreName;

    public SimpleBookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorName = book.getAuthor().getName();
        this.genreName = book.getGenre().getName();
    }
}
