package ru.est0y.domain.mongo;

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
public class BookDocument {

    @Id
    private final String id;

    private final String name;

    @DBRef
    private final AuthorDocument authorDocument;

    private final GenreDocument genreDocument;
}
