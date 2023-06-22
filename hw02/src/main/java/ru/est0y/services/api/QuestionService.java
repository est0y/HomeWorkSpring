package ru.est0y.services.api;

import ru.est0y.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}
