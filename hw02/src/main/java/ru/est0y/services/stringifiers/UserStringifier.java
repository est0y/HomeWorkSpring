package ru.est0y.services.stringifiers;

import org.springframework.stereotype.Service;
import ru.est0y.domain.User;
import ru.est0y.services.api.Stringifier;

@Service
public class UserStringifier implements Stringifier<User> {

    @Override
    public String stringify(User user) {
        return String.format("%s %s", user.getFirstName(), user.getSecondName());
    }
}
