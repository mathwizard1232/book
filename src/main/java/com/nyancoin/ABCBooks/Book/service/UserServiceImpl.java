package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.CrudDao;
import com.nyancoin.ABCBooks.Book.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final CrudDao<User> userDao;

    @Autowired
    public UserServiceImpl(final CrudDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public int add(final User user) { return userDao.add(user); }

    @Override
    public void delete(final Long id) {
        // TODO implement
        return;
    }

    @Override
    public User get(final Long id) {
        // TODO implement
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO implement
        return new ArrayList<>();
    }
}
