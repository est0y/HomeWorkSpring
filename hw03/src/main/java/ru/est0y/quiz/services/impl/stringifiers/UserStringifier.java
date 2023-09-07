package ru.est0y.quiz.services.impl.stringifiers;

import org.springframework.stereotype.Service;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.Stringifier;

@Service
public class UserStringifier implements Stringifier<User> {

    @Override
    public String stringify(User user) {
        return String.format("%s %s", user.getFirstName(), user.getSecondName());
    }
}
