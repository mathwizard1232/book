package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.AuthorDao;
import com.nyancoin.ABCBooks.Book.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private AuthorDao dao;

    @Autowired
    public AuthorService(final AuthorDao dao) {
        this.dao = dao;
    }

    public List<Author> getAll() { return dao.getAll(); }

    public Author get(final Long id) { return dao.get(id); }

    public Long getOrAdd(final Author author) {
        if (dao.exists(author)) {
            return dao.getId(author);
        } else {
            return dao.add(author);
        }
    }
}
