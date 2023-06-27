package ru.est0y.services.api;

import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Stats;
import ru.est0y.domain.User;

import java.util.List;

public interface StatsService {
    Stats getStats(User user, List<AnsweredQuestion> list);
}
