package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.BookDaoImpl;
import com.nyancoin.ABCBooks.Book.dao.CrudDao;
import com.nyancoin.ABCBooks.Book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    // Depend directly upon the actual implementation because I don't see any reason to copy over every signature change to an interface when this is not going to be polymorphic
    private BookDaoImpl bookDao;

    @Autowired
    public BookServiceImpl(final BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Long add(final Book book) {
        // TODO implement
        return null;
    }

    public String add(String author, String title, String boxid)
    {
        return "";
    }

    public int getOrAdd(int author_id, String title)
    {
        if (bookDao.exists(author_id, title)) {
            return bookDao.getId(title,author_id);
        } else {
            return bookDao.add(title, author_id);
        }
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
