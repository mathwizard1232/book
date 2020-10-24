package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.domain.Box;
import com.nyancoin.ABCBooks.Book.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/box")
@RestController
public class BoxController {

    private BoxService boxService;

    @Autowired
    public BoxController(final BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping("/add")
    public void add(@RequestBody final Box box) {
        boxService.add(box);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Box get(@PathVariable final Long id) { return boxService.get(id); }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Box> getAll() { return boxService.getAll(); }
}
