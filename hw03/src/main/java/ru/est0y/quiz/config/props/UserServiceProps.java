package ru.est0y.quiz.config.props;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.est0y.quiz.config.Properties;

@Component
@Getter
public class UserServiceProps {
    private final String firstNamePrompt;

    private final String lastNamePrompt;


    public UserServiceProps(Properties properties) {
        this.firstNamePrompt = properties.getProperty("userService.enterNameText");
        this.lastNamePrompt = properties.getProperty("userService.enterLastNameText");
    }

}
