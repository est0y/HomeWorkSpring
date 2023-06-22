package ru.est0y.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.User;
import ru.est0y.services.api.IOService;
import ru.est0y.services.api.UserService;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    @Override
    public User getUser() {
        ioService.print("Enter your name:");
        String name = ioService.read();
        ioService.print("Enter last name");
        String lastName = ioService.read();
        return new User(name, lastName);
    }
}
