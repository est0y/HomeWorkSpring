package ru.est0y.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Question;
import ru.est0y.services.api.IOService;
import ru.est0y.services.api.QuestionAsker;
import ru.est0y.services.api.Stringifier;

import java.util.List;

@Service
@AllArgsConstructor
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
