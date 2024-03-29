package ru.est0y.quiz.services.impl;

import lombok.RequiredArgsConstructor;
import ru.est0y.quiz.services.IOService;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class IOServiceImpl implements IOService {
    private final Scanner scanner;

    private final PrintStream output;

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void print(String string) {
        output.println(string);
    }

    @Override
    public void print(List<String> list) {
        list.forEach(this::print);
    }
}
