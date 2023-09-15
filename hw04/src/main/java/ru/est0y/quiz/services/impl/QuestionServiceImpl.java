package ru.est0y.quiz.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.quiz.domain.Answer;
import ru.est0y.quiz.domain.Question;
import ru.est0y.quiz.services.Parser;
import ru.est0y.quiz.services.QuestionService;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final Parser csvParser;

    @Override
    public List<Question> getAllQuestions() {
        return csvParser.parse().stream().map(this::getQuestion).toList();
    }

    private Question getQuestion(List<String> list) {
        if (list.size() < 3) {
            throw new InvalidParameterException("Not enough strings to create instance of Question");
        }
        var answer = new Answer(list.get(1), true);
        var options = new ArrayList<>(list.subList(2, list.size()).stream().
                map(str -> new Answer(str, false))
                .toList());
        options.add(answer);
        Collections.shuffle(options);
        return new Question(list.get(0), options);
    }
}
