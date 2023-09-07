package ru.est0y.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.est0y.quiz.config.props.LocaleProp;
import ru.est0y.quiz.services.IOService;
import ru.est0y.quiz.services.Parser;
import ru.est0y.quiz.services.impl.CsvParser;
import ru.est0y.quiz.services.impl.IOServiceImpl;

import java.io.PrintStream;
import java.util.Scanner;

@EnableConfigurationProperties(LocaleProp.class)
@Configuration
public class AppConfig {

    @Bean
    public Parser parser(@Value("${application.questions.filePath}") String filePath) {
        return new CsvParser(filePath);
    }

    @Bean
    public IOService ioService(@Value("#{T(java.lang.System).out}")
                               PrintStream printStream,
                               @Value("#{new java.util.Scanner(T(java.lang.System).in)}")
                               Scanner scanner) {

        return new IOServiceImpl(scanner, printStream);
    }

}
