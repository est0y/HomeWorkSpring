package ru.est0y.services.api;

import java.util.List;

public interface Stringifier<T> {
    List<String> stringify(List<T> list);

    String stringify(T object);
}
