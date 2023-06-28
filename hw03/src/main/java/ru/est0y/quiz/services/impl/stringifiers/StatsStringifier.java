package ru.est0y.quiz.services.impl.stringifiers;

import lombok.AllArgsConstructor;

import ru.est0y.quiz.domain.Stats;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.Stringifier;


@AllArgsConstructor
public class StatsStringifier implements Stringifier<Stats> {
    private final Stringifier<User> userStringifier;

    private final String statsFormatString;

    @Override
    public String stringify(Stats stats) {
        String userString = userStringifier.stringify(stats.getUser());
        return String.format(statsFormatString,
                userString,
                stats.getCorrectAnswerCount(),
                stats.getQuestionCount()
        );
    }
}
