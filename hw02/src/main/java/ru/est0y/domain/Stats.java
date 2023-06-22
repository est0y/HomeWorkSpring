package ru.est0y.domain;

import lombok.Data;

@Data
public class Stats {
    private final User user;

    private final int correctAnswerCount;

    private final int questionCount;
}

