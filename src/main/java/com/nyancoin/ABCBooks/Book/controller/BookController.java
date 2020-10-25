package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.UIO;
import com.nyancoin.ABCBooks.Book.domain.Book;
import com.nyancoin.ABCBooks.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UIO UIO;

    @PostMapping("/add")
    public void add(@RequestBody final Book book) { bookService.add(book); }

    @GetMapping("/add")
    public String add() {
        String result = UIO.getHeader("Add a Book");
        result += UIO.basicBookInput("");
        result += UIO.getFooter();
        return result;
    }
}
