package ru.est0y.quiz.domain;

import lombok.Data;

import java.util.List;


@Data
public class Question {
    private final String question;

    private final List<Answer> options;
}
