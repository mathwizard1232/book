package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.CrudDao;
import com.nyancoin.ABCBooks.Book.domain.Box;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoxServiceImpl implements BoxService {

    private final CrudDao<Box> boxDao;

    @Autowired
    public BoxServiceImpl(final CrudDao<Box> boxDao) {
        this.boxDao = boxDao;
    }

    @Override
    public Long add(final Box box) {
        // TODO implement
        return null;
    }

    @Override
    public void delete(final Long id) {
        // TODO implement
        return;
    }

    @Override
    public Box get(final Long id) {
        // TODO implement
        return null;
    }

    @Override
    public List<Box> getAll() {
        // TODO implement
        return null;
    }
}
