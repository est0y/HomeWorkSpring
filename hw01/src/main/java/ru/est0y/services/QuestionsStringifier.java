package ru.est0y.services;

import ru.est0y.domain.Answer;
import ru.est0y.domain.Question;
import ru.est0y.services.api.Stringifier;

import java.util.List;
import java.util.stream.Collectors;


public class QuestionsStringifier implements Stringifier<Question> {
    @Override
    public List<String> stringify(List<Question> questionWithOptions) {
        return questionWithOptions.stream().map(this::stringify).toList();
    }

    @Override
    public String stringify(Question question) {
        String questionText = question.getQuestion();
        var options = question.getOptions().stream().map(Answer::getAnswerText)
                .map(s -> " " + s)
                .collect(Collectors.joining("\n"));
        return "\n" + questionText + "\n" + options;
    }


}
