package ru.est0y.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCommentDto {

    private long id;

    private String text;
}
