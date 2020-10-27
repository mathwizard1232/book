package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Box;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoxDaoImpl implements CrudDao<Box> {
    @Override
    public int add(final Box box) {
        // TODO implement
        return -1;
    }

    @Override
    public boolean exists(final Box box) {
        // TODO implement
        return false;
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

    @Override
    public int getId(final Box box) {
        // TODO implement
        return -1;
    }
}
