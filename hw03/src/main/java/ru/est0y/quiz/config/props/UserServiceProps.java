package ru.est0y.quiz.config.props;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.est0y.quiz.config.Properties;

@Component
@Getter
public class UserServiceProps {
    private final String enterNameText;

    private final String enterLastNameText;


    public UserServiceProps(Properties properties) {
        this.enterNameText = properties.getProperty("userService.enterNameText");
        this.enterLastNameText = properties.getProperty("userService.enterLastNameText");
    }

}
