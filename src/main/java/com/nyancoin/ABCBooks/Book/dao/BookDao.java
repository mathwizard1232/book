package com.nyancoin.ABCBooks.Book.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
// It's your dao, you can decide whether it has responsibility for both the book and book_contents tables
public class BookDao {

    private final NamedParameterJdbcTemplate template;

    public BookDao(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }
}
