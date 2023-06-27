package ru.est0y;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AppRunner {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppRunner.class);
        context.getBean(App.class).run();
    }
}
