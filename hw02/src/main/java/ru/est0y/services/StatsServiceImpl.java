package ru.est0y.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Stats;
import ru.est0y.domain.User;
import ru.est0y.services.api.AnswerChecker;
import ru.est0y.services.api.StatsService;

import java.util.List;

@Service
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final AnswerChecker answerChecker;

    @Override
    public Stats getStats(User user, List<AnsweredQuestion> list) {
        int correctAnswersCount = (int) list.stream().filter(answerChecker::isCorrect).count();
        var countQuestions = list.size();
        return new Stats(user, correctAnswersCount, countQuestions);
    }
}
