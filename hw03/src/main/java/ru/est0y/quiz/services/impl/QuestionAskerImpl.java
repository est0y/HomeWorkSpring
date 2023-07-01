package ru.est0y.quiz.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.quiz.domain.AnsweredQuestion;
import ru.est0y.quiz.domain.Question;
import ru.est0y.quiz.services.IOService;
import ru.est0y.quiz.services.QuestionAsker;
import ru.est0y.quiz.services.Stringifier;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionAskerImpl implements QuestionAsker {
    private final IOService ioService;

    private final Stringifier<Question> stringifier;

    @Override
    public AnsweredQuestion ask(Question question) {
        printQuestion(question);
        return new AnsweredQuestion(question, askQuestion());
    }

    @Override
    public List<AnsweredQuestion> ask(List<Question> questions) {
        return questions.stream().map(this::ask).toList();
    }

    private void printQuestion(Question question) {
        ioService.print(stringifier.stringify(question));
    }

    private String askQuestion() {
        return ioService.read();
    }
}
