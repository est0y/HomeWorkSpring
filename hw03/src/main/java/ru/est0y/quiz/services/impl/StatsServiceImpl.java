package ru.est0y.quiz.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.quiz.domain.AnsweredQuestion;
import ru.est0y.quiz.domain.Stats;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.AnswerChecker;
import ru.est0y.quiz.services.StatsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final AnswerChecker answerChecker;

    @Override
    public Stats getStats(User user, List<AnsweredQuestion> list) {
        int correctAnswersCount = (int) list.stream().filter(answerChecker::isCorrect).count();
        var countQuestions = list.size();
        return new Stats(user, correctAnswersCount, countQuestions);
    }
}
