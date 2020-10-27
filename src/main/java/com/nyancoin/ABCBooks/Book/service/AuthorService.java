package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.domain.Author;

import java.util.List;

public interface AuthorService {
    void delete(final Long id);
    List<Author> getAll();
    Author get(final Long id);
    int getOrAdd(final Author author);
    int getOrAdd(String searchName);
}
