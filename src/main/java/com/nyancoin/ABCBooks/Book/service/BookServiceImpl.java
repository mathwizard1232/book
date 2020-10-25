package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.CrudDao;
import com.nyancoin.ABCBooks.Book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private CrudDao<Book> bookDao;

    @Autowired
    public BookServiceImpl(final CrudDao<Book> bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Long add(final Book book) {
        // TODO implement
        return null;
    }

    public void delete(final Long id) {
        // TODO implement
        return;
    }

    @Override
    public Book get(final Long id) {
        // TODO implement
        return null;
    }

    @Override
    public List<Book> getAll() {
        // TODO implement
        return new ArrayList<>();
    }
}
