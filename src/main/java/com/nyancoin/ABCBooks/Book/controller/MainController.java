package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.UIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final UIO uio;
    // Prefer autowiring into constructors over fields
    // Technically the annotation can be omitted on a class with a single constructor, but I like the obviousness
    // of having it
    @Autowired
    public MainController(final UIO uio) {
        this.uio = uio;
    }

    @RequestMapping("/")
    public String index() {
        return uio.basicPage("Greetings from ABC Books!<br><br><a href=\"box/add\">Add a box of books.</a><br>" +
                                   "<br><a href=\"book/add\">Add a book.</a><br><br><a href=\"review\">View current" +
                                   "collection.</a>");
    }

    // Endpoint kept for nostalgia
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return String.format("Hello %s!", name);
    }
}
