package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.domain.User;

import java.util.List;

public interface UserService {
    int add(final User user);
    void delete(final Long id);
    User get(final Long id);
    List<User> getAll();
}
