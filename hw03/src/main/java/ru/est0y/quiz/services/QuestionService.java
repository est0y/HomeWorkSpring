package ru.est0y.quiz.services;

import ru.est0y.quiz.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}
