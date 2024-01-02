package ru.est0y.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PagesController {

    @GetMapping({"/", "/books"})
    public String getBooks() {
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(Model model,@PathVariable("id") String id) {
        model.addAttribute("id",id);
        return "edit";
    }

    @GetMapping("/creation")
    public String creationBook() {
        return "creation";
    }
}
