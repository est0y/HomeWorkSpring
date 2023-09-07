package ru.est0y.quiz.domain;

import lombok.Data;

@Data
public class Answer {
    private final String answerText;

    private final boolean corrected;
}
