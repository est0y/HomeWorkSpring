package ru.est0y.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Caterpillar;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Runner {

    private final ButterflyTransformGateway butterflyTransformGateway;


    public void run() {
        var butterflies = butterflyTransformGateway.process(
                List.of(new Caterpillar("1"),
                        new Caterpillar("2"),
                        new Caterpillar("3"),
                        new Caterpillar("4"),
                        new Caterpillar("5"))
        );
        log.info(butterflies.toString());
    }

}
