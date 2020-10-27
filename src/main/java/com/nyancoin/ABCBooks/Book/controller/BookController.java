package com.nyancoin.ABCBooks.Book.controller;

import com.nyancoin.ABCBooks.Book.UIO;
import com.nyancoin.ABCBooks.Book.domain.Book;
import com.nyancoin.ABCBooks.Book.service.AuthorService;
import com.nyancoin.ABCBooks.Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UIO UIO;

    @PostMapping("/add")
    public String add(@RequestParam(value = "author", defaultValue = "") String author,
                    @RequestParam(value = "title", defaultValue = "") String title,
                    @RequestParam(value = "boxid", defaultValue = "") String boxid) {
        // If we've got complete information, proceed
        if (!(author.isEmpty()) && !(title.isEmpty())) {
            // First get the id for the author
            int aid = authorService.getOrAdd(author);
            //return aid.toString();
            //return bookService.add(author, title, boxid);
            // Integer book_id = bookService.LookupOrAdd(aid, title);
            Integer book_id = bookService.getOrAdd(aid, title);
            return book_id.toString();
        } else {
            return "Incomplete information.";
        }
    }

    @GetMapping("/add")
    public String add() {
        String result = UIO.getHeader("Add a Book");
        result += UIO.basicBookInput("");
        result += UIO.getFooter();
        return result;
    }
}
