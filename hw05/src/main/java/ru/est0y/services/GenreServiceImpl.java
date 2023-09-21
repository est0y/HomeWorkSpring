package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Genre;
import ru.est0y.repositories.GenreDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
