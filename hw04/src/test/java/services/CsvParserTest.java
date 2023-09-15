package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.est0y.quiz.Quiz;
import ru.est0y.quiz.config.AppConfig;
import ru.est0y.quiz.services.impl.CsvParser;

@SpringBootConfiguration
@SpringBootTest
class CsvParserTest {
    @Configuration
    static class Config {
        @Bean
        public CsvParser csvParser(@Value("${application.questions.filePath}") String filePath) {
            return new CsvParser(filePath);
        }
    }

    @Autowired
    private CsvParser csvParser;

    @Test
    void parse() {
        var questionWithAnswers = csvParser.parse();
        System.out.println(questionWithAnswers);
        Assertions.assertEquals(2, questionWithAnswers.size());
        Assertions.assertEquals(3, questionWithAnswers.get(0).size());
        Assertions.assertEquals(3, questionWithAnswers.get(1).size());
        Assertions.assertEquals("Question 1", questionWithAnswers.get(0).get(0));
        Assertions.assertEquals("CorrectAnswer", questionWithAnswers.get(0).get(1));
        Assertions.assertEquals("NotCorrectAnswer", questionWithAnswers.get(0).get(2));
    }

}