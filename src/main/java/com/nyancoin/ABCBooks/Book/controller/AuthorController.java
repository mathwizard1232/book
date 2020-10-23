package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.domain.Author;
import com.nyancoin.ABCBooks.Book.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/author")
@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAll() {
        return authorService.getAll();
    }
}
