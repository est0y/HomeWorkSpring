package ru.est0y.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.services.AuthorService;
import ru.est0y.services.BookService;
import ru.est0y.services.GenreService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping({"/", "/books"})
    public String getBooks(Model model) {
        var books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(Model model, @RequestParam("id") String id) {
        var genres = genreService.findAll();
        var authors = authorService.findAll();
        var book = bookService.findById(id).orElseThrow();
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "edit";
    }

    @PostMapping("/update")
    public String updateBook(@Valid BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/creation")
    public String creationBook(Model model) {
        var genres = genreService.findAll();
        var authors = authorService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "creation";
    }

    @PostMapping("/create")
    public String createBook(@Valid BookDtoWithoutId bookDto) {
        bookService.insert(bookDto);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
