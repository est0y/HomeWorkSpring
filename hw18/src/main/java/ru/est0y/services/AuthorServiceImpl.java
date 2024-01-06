package ru.est0y.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Author;
import ru.est0y.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @HystrixCommand(fallbackMethod = "getEmptyAuthor")
    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @HystrixCommand(fallbackMethod = "getEmptyAuthors")
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    private Optional<Author> getEmptyAuthor(String id) {
        return Optional.of(new Author(id, "N/A"));
    }

    private List<Author> getEmptyAuthors() {
        return List.of(getEmptyAuthor("0").orElseThrow());
    }
}
