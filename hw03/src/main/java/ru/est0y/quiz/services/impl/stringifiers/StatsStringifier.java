package ru.est0y.quiz.services.impl.stringifiers;

import org.springframework.stereotype.Service;
import ru.est0y.quiz.config.props.StatsStringifierProps;
import ru.est0y.quiz.domain.Stats;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.Stringifier;

@Service

public class StatsStringifier implements Stringifier<Stats> {
    private final Stringifier<User> userStringifier;

    private final String statsFormatString;

    public StatsStringifier(Stringifier<User> userStringifier, StatsStringifierProps props) {
        this.userStringifier = userStringifier;
        this.statsFormatString = props.getStatsStringFormat();
    }

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
