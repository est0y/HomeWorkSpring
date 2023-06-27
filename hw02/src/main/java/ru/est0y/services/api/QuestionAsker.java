package ru.est0y.services.api;

import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Question;


import java.util.List;


public interface QuestionAsker {
    AnsweredQuestion ask(Question question);

    List<AnsweredQuestion> ask(List<Question> questions);
}
