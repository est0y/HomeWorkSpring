package ru.est0y.quiz.services.impl;

import lombok.AllArgsConstructor;
import ru.est0y.quiz.domain.User;
import ru.est0y.quiz.services.IOService;
import ru.est0y.quiz.services.UserService;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final String enterNameText;

    private final String enterLastNameText;

    private final IOService ioService;


    @Override
    public User getUser() {
        ioService.print(enterNameText);
        String name = ioService.read();
        ioService.print(enterLastNameText);
        String lastName = ioService.read();
        return new User(name, lastName);
    }
}
