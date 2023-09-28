package ru.est0y.services.stringifiers;

import java.util.List;

public interface Stringifier<T> {
    String stringify(T object);

    default String stringify(List<T> objects) {
        String result = "";
        for (var object : objects) {
            result = result.concat(stringify(object)) + "\n";
        }
        return result;
    }
}
