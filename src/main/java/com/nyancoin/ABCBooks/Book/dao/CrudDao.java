package com.nyancoin.ABCBooks.Book.dao;

import java.util.List;

public interface CrudDao<T> {
    int add(final T t);
    void delete(final Long id);
    boolean exists(final T t);
    T get(final Long id);
    List<T> getAll();
    int getId(final T t);
}
