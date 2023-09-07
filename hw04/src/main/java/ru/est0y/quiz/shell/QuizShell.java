package ru.est0y.quiz.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.est0y.quiz.Quiz;

@ShellComponent
@RequiredArgsConstructor
public class QuizShell {
    private final Quiz quiz;

    @ShellMethod(key = {"st", "start-test"}, value = "Start test")
    public void startTest() {
        quiz.run();
    }
}
