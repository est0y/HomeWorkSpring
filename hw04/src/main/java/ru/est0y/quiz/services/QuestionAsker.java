package ru.est0y.quiz.services;

import ru.est0y.quiz.domain.AnsweredQuestion;
import ru.est0y.quiz.domain.Question;

import java.util.List;


public interface QuestionAsker {
    AnsweredQuestion ask(Question question);

    List<AnsweredQuestion> ask(List<Question> questions);
}
