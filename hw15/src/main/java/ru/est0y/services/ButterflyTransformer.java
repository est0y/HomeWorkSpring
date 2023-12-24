package ru.est0y.services;

import ru.est0y.domain.Butterfly;
import ru.est0y.domain.Caterpillar;

public interface ButterflyTransformer {
    Butterfly transform(Caterpillar caterpillar);
}
