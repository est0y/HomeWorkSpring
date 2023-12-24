package ru.est0y.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.est0y.domain.Butterfly;
import ru.est0y.domain.Caterpillar;

@Service
@Slf4j
public class ButterflyTransformerImpl implements ButterflyTransformer {
    @Override
    public Butterfly transform(Caterpillar caterpillar) {
        log.info(caterpillar + " transform to butterfly");
        return new Butterfly(caterpillar.getName());
    }
}
