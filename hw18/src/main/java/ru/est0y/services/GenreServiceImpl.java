package ru.est0y.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Genre;
import ru.est0y.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @HystrixCommand(fallbackMethod = "getEmptyGenre")
    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @HystrixCommand(fallbackMethod = "getEmptyGenres")
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    private Optional<Genre> getEmptyGenre(String id) {
        return Optional.of(new Genre(id, "N/A"));
    }

    private List<Genre> getEmptyGenres() {
        return List.of(getEmptyGenre("0").orElseThrow());
    }
}
