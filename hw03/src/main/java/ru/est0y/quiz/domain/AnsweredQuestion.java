package ru.est0y.quiz.domain;

import lombok.Data;

@Data
public class AnsweredQuestion {
    private final Question question;

    private final String answer;
}
