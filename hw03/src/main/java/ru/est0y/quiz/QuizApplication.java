package ru.est0y.quiz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(QuizApplication.class, args);
        context.getBean(Quiz.class).run();
    }
}
