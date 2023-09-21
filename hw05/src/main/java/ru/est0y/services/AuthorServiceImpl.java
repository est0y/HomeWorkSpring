package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Author;
import ru.est0y.repositories.AuthorDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}
