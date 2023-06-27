package ru.est0y.services;

import lombok.AllArgsConstructor;
import ru.est0y.services.api.IOService;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
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
