package ru.est0y.quiz.config.props;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Locale;

@Getter
@ConfigurationProperties("application")
public class LocaleProp {
    private final Locale locale;

    @ConstructorBinding
    public LocaleProp(Locale locale) {
        this.locale = locale;
    }

}
