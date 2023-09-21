package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import ru.est0y.quiz.services.Parser;
import ru.est0y.quiz.services.impl.QuestionServiceImpl;

import java.security.InvalidParameterException;
import java.util.List;

@SpringBootTest(classes = QuestionServiceImpl.class)
class QuestionServiceImplTest {
    @MockBean
    private Parser parser;
    @Autowired
    private QuestionServiceImpl questionService;

    @Test
    void getAllQuestions() {
        Mockito.when(parser.parse()).thenReturn(List.of(
                List.of("Question", "Option1", "Option2"),
                List.of("Question2", "Option1", "Option2")
        ));
        var result = questionService.getAllQuestions();
        var question = result.get(0);
        Assertions.assertEquals("Question", question.getQuestion());
        Assertions.assertEquals(2, question.getOptions().size());
        var question2 = result.get(1);
        Assertions.assertEquals("Question2", question2.getQuestion());
        Assertions.assertEquals(2, question2.getOptions().size());
    }

    @Test
    void getAllQuestionsWithInvalidStringsCount() {
        Mockito.when(parser.parse()).thenReturn(List.of(List.of("Question", "Option")));
        Assertions.assertThrows(InvalidParameterException.class, () -> questionService.getAllQuestions());

        Mockito.when(parser.parse()).thenReturn(List.of(List.of("Question")));
        Assertions.assertThrows(InvalidParameterException.class, () -> questionService.getAllQuestions());

        Mockito.when(parser.parse()).thenReturn(List.of(List.of()));
        Assertions.assertThrows(InvalidParameterException.class, () -> questionService.getAllQuestions());
    }
}