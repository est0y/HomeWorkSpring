package ru.est0y.quiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.est0y.quiz.config.props.LocaleProp;


@Component
@RequiredArgsConstructor
public class Properties {
    private final MessageSource messageSource;

    private final LocaleProp localeProp;

    public String getProperty(String code) {
        return getProperty(code, new String[]{});
    }

    public String getProperty(String code, String... args) {
        return messageSource.getMessage(code, args, localeProp.getLocale());
    }
}
