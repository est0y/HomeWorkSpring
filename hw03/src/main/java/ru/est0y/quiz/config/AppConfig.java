package ru.est0y.quiz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.est0y.quiz.config.props.LocaleProp;
import ru.est0y.quiz.config.props.StatsStringifierProps;
import ru.est0y.quiz.config.props.UserServiceProps;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.impl.CsvParser;
import ru.est0y.quiz.services.impl.IOServiceImpl;
import ru.est0y.quiz.services.impl.UserServiceImpl;
import ru.est0y.quiz.services.IOService;
import ru.est0y.quiz.services.Parser;
import ru.est0y.quiz.services.Stringifier;
import ru.est0y.quiz.services.UserService;
import ru.est0y.quiz.services.impl.stringifiers.StatsStringifier;

import java.io.PrintStream;
import java.util.Scanner;

@EnableConfigurationProperties(LocaleProp.class)
@Configuration
public class AppConfig {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final PrintStream PRINT_STREAM = System.out;

    @Bean
    public UserService userServiceImpl(UserServiceProps userServiceProps, IOService ioService) {
        String enterNameText = userServiceProps.getEnterNameText();
        String enterLastNameText = userServiceProps.getEnterLastNameText();
        return new UserServiceImpl(enterNameText, enterLastNameText, ioService);
    }

    @Bean
    public Parser parser(@Value("${application.questions.filePath}") String filePath) {
        return new CsvParser(filePath);
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(SCANNER, PRINT_STREAM);
    }

    @Bean
    StatsStringifier statsStringifier(Stringifier<User> stringifier, StatsStringifierProps props) {
        return new StatsStringifier(stringifier,props.getStatsStringFormat());
    }
}
