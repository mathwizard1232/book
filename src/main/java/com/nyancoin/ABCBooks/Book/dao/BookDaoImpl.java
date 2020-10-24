package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// It's your dao, you can decide whether it has responsibility for both the book and book_contents tables
// In which case you can delete BookContentsDao
public class BookDaoImpl implements CrudDao<Book> {

    private final NamedParameterJdbcTemplate template;
    private final RowMapper<Book> bookMapper;

    // TODO create a mapper that fits this signature
    public BookDaoImpl(final NamedParameterJdbcTemplate template, final RowMapper<Book> bookMapper) {
        this.template = template;
        this.bookMapper = bookMapper;
    }

    @Override
    public Long add(final Book book) {
        // TODO implement
        return null;
    }

    @Override
    public void delete(final Long id) {
        // TODO implement
        return;
    }

    @Override
    public boolean exists(final Book book) {
        // TODO implement
        // return !template.query(sql, params, Integer.class).isEmpty();
        return false;
    }

    @Override
    public Book get(final Long id) {
        // TODO implement
        return null;
    }

    @Override
    public List<Book> getAll() {
        // TODO implement
        return null;
    }

    @Override
    public Long getId(final Book book) {
        // TODO implement
        return null;
    }
}
