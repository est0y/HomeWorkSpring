package ru.est0y.services;

import ru.est0y.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String name);
}
