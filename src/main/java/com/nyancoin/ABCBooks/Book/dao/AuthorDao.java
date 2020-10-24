package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Author;

import java.util.List;

public interface AuthorDao {
    Long add(final Author author);
    boolean exists(final Author author);
    void delete(final Long id);
    Author get(final Long id);
    List<Author> getAll();
    Long getId(Author author);
}
