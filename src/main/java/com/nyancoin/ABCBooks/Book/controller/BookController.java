package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.domain.Book;
import com.nyancoin.ABCBooks.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/book")
@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public void add(@RequestBody final Book book) { bookService.add(book); }
}
