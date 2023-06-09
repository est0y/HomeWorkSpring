package ru.est0y.services;

import lombok.AllArgsConstructor;
import ru.est0y.services.api.Printer;

import java.io.PrintStream;
import java.util.List;

@AllArgsConstructor
public class PrinterImpl implements Printer {
    private final PrintStream out;

    public void print(List<String> list) {
        list.forEach(out::println);
    }

}
