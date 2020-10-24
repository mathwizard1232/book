package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.domain.Box;

import java.util.List;

public interface BoxService {
    Long add(final Box box);
    void delete(final Long id);
    Box get(final Long id);
    List<Box> getAll();
}
