package com.nyancoin.ABCBooks.Book.dao;

import java.util.List;

public interface CrudDao<T> {
    Long add(final T t);
    void delete(final Long id);
    boolean exists(final T t);
    T get(final Long id);
    List<T> getAll();
    Long getId(final T t);
}
