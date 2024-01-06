package ru.est0y.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.est0y.domain.Book;
import ru.est0y.dto.BookDto;
import ru.est0y.dto.BookDtoWithoutId;
import ru.est0y.dto.SimpleBookDto;
import ru.est0y.services.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookService.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<List<SimpleBookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll().stream()
                .map((SimpleBookDto::new)).toList(), HttpStatus.OK);
    }

    @PutMapping("/book")
    public ResponseEntity<Void> updateBook(@Valid @RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/book")
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookDtoWithoutId bookDto) {
        bookService.insert(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
