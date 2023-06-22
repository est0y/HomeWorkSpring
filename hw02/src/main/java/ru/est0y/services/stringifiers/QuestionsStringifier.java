package ru.est0y.services.stringifiers;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Answer;
import ru.est0y.domain.Question;
import ru.est0y.services.api.Stringifier;

import java.util.stream.Collectors;

@Service
public class QuestionsStringifier implements Stringifier<Question> {

    @Override
    public String stringify(Question question) {
        String questionText = question.getQuestion();
        var options = question.getOptions().stream().map(Answer::getAnswerText)
                .map(s -> " " + s)
                .collect(Collectors.joining("\n"));
        return "\n" + questionText + "\n" + options;
    }


}
