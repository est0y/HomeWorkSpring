package ru.est0y.services.stringifiers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Stats;
import ru.est0y.domain.User;
import ru.est0y.services.api.Stringifier;

@Service
@AllArgsConstructor
public class StatsStringifier implements Stringifier<Stats> {
    private final Stringifier<User> userStringifier;

    @Override
    public String stringify(Stats stats) {
        String userString = userStringifier.stringify(stats.getUser());
        return String.format("%s answered %d/%d questions correctly",
                userString,
                stats.getCorrectAnswerCount(),
                stats.getQuestionCount()
        );
    }
}
