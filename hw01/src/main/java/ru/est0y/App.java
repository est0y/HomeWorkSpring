package ru.est0y;

import lombok.RequiredArgsConstructor;
import ru.est0y.domain.Question;
import ru.est0y.services.api.Printer;
import ru.est0y.services.api.QuestionService;
import ru.est0y.services.api.Stringifier;

import java.util.List;

@RequiredArgsConstructor
public class App {

    private final QuestionService questionService;

    private final Stringifier<Question> stringifier;

    private final Printer printer;

    public void run() {
        List<String> strings = stringifier.stringify(questionService.getAllQuestions());
        printer.print(strings);
    }

}
