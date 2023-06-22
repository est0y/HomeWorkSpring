package ru.est0y.services;

import org.springframework.stereotype.Service;
import ru.est0y.domain.Answer;
import ru.est0y.domain.AnsweredQuestion;
import ru.est0y.domain.Question;
import ru.est0y.services.api.AnswerChecker;

@Service
public class AnswerCheckerImpl implements AnswerChecker {
    @Override
    public boolean isCorrect(AnsweredQuestion answeredQuestion) {
        Question question = answeredQuestion.getQuestion();
        String userAnswer = answeredQuestion.getAnswer();
        return question.getOptions().stream()
                .filter(Answer::isCorrected)
                .anyMatch(answer -> answer.getAnswerText().equalsIgnoreCase(userAnswer));
    }
}
