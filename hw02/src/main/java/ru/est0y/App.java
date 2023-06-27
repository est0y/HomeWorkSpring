package ru.est0y;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Stats;
import ru.est0y.domain.User;
import ru.est0y.services.api.*;

import java.util.List;

@Service
@AllArgsConstructor
public class App {
    private final QuestionService questionService;

    private final QuestionAsker questionAsker;

    private final UserService userService;

    private final StatsService statsService;

    private final Stringifier<Stats> statsStringifier;


    public void run() {
        User user = userService.getUser();
        List<AnsweredQuestion> answers = questionAsker.ask(questionService.getAllQuestions());
        String result = statsStringifier.stringify(statsService.getStats(user, answers));
        System.out.println(result);
    }

}
