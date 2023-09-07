package ru.est0y.quiz.services;

import java.util.List;

public interface Stringifier<T> {
    default List<String> stringify(List<T> list) {
        return list.stream().map(this::stringify).toList();
    }

    String stringify(T object);
}
