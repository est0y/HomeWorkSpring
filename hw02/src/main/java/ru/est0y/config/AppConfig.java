package ru.est0y.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.est0y.services.CsvParser;
import ru.est0y.services.IOServiceImpl;
import ru.est0y.services.api.IOService;
import ru.est0y.services.api.Parser;

import java.io.PrintStream;
import java.util.Scanner;

@PropertySource("app.properties")
@Configuration
public class AppConfig {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final PrintStream PRINT_STREAM = System.out;

    @Bean
    public Parser parser(@Value("${questions.path}") String path) {
        return new CsvParser(path);
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(SCANNER, PRINT_STREAM);
    }
}
