package ru.est0y.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Document("genres")
public class Genre {
    @Id
    private final String id;

    private final String name;
}
