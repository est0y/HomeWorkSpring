package ru.est0y.quiz.services;

import ru.est0y.quiz.domain.AnsweredQuestion;

public interface AnswerChecker {
    boolean isCorrect(AnsweredQuestion answeredQuestion);

}
