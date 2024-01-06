package ru.est0y.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.est0y.repositories.BookRepository;

@Component
@RequiredArgsConstructor
public class BooksCountHealthCheck implements HealthIndicator {

    private final BookRepository bookRepository;

    @Override
    public Health health() {
        try {
            return bookRepository.count() != 0 ? Health.up().build() : Health.down()
                    .withDetail("message", "book count equals 0").build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }
}
