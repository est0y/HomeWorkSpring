package ru.est0y.domain;

import lombok.Data;

@Data
public class AnsweredQuestion {
    private final Question question;

    private final String answer;
}
