package ru.est0y.quiz.services.impl;

import org.springframework.stereotype.Service;
import ru.est0y.quiz.config.props.UserServiceProps;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.IOService;
import ru.est0y.quiz.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final String firstNamePrompt;

    private final String lastNamePrompt;

    private final IOService ioService;

    public UserServiceImpl(IOService ioService, UserServiceProps props) {
        this.ioService = ioService;
        this.firstNamePrompt = props.getFirstNamePrompt();
        this.lastNamePrompt = props.getLastNamePrompt();
    }


    @Override
    public User getUser() {
        ioService.print(firstNamePrompt);
        String name = ioService.read();
        ioService.print(lastNamePrompt);
        String lastName = ioService.read();
        return new User(name, lastName);
    }
}
