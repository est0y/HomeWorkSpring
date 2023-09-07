package ru.est0y.quiz.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.est0y.quiz.services.impl.CsvParser;


class CsvParserTest {

    @Test
    void parse() {
        var questionWithAnswers = new CsvParser("questions.csv").parse();
        Assertions.assertEquals(2, questionWithAnswers.size());
        Assertions.assertEquals(3, questionWithAnswers.get(0).size());
        Assertions.assertEquals(3, questionWithAnswers.get(1).size());
        Assertions.assertEquals("Question 1", questionWithAnswers.get(0).get(0));
        Assertions.assertEquals("CorrectAnswer", questionWithAnswers.get(0).get(1));
        Assertions.assertEquals("NotCorrectAnswer", questionWithAnswers.get(0).get(2));
    }

}