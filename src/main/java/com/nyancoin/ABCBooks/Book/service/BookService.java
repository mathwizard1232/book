package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.domain.Book;

import java.util.List;

public interface BookService {
    Long add(final Book book);
    String add(String author, String title, String boxid);
    void delete(final Long bookId);
    Book get(final Long id);
    List<Book> getAll();
    int getOrAdd(int authorId, String title);
}
