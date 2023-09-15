package ru.est0y.quiz.config.props;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.est0y.quiz.config.Properties;

@Getter
@Component
public class StatsStringifierProps {

    private final String statsStringFormat;

    public StatsStringifierProps(Properties properties) {
        this.statsStringFormat = properties.getProperty("statsStringifier.stringFormat");
    }
}
