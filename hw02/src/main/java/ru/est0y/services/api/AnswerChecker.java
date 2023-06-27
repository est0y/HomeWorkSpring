package ru.est0y.services.api;

import ru.est0y.domain.AnsweredQuestion;

public interface AnswerChecker {
    boolean isCorrect(AnsweredQuestion answeredQuestion);

}
