package ru.est0y.quiz.services;

import ru.est0y.quiz.domain.AnsweredQuestion;
import ru.est0y.quiz.domain.Stats;
import ru.est0y.quiz.domain.User;

import java.util.List;

public interface StatsService {
    Stats getStats(User user, List<AnsweredQuestion> list);
}
