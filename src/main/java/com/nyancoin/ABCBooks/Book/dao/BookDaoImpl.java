package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Author;
import com.nyancoin.ABCBooks.Book.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// It's your dao, you can decide whether it has responsibility for both the book and book_contents tables
// In which case you can delete BookContentsDao
public class BookDaoImpl implements CrudDao<Book> {

    private final NamedParameterJdbcTemplate template;
//    private final RowMapper<Book> bookMapper;

    // TODO create a mapper that fits this signature
    public BookDaoImpl(final NamedParameterJdbcTemplate template) {//}, final RowMapper<Book> bookMapper) {
        this.template = template;
 //       this.bookMapper = bookMapper;
    }

    @Override
    public int add(final Book book) {
        // TODO implement
        return -1;
    }

    @Override
    public void delete(final Long id) {
        // TODO implement
        return;
    }

    public boolean exists(int author_id, String search_title)
    {
        final String sql = " SELECT 1" +
                " FROM BOOKS" +
                " WHERE search_title = :title AND author = :author_id" +
                " LIMIT 1"; // FETCH FIRST 1 ROWS ONLY depending on your RDMBS of choice

        final SqlParameterSource params = new MapSqlParameterSource("author_id", author_id)
                .addValue("title", search_title);
        return !template.queryForList(sql, params, Integer.class).isEmpty();
    }

    @Override
    public boolean exists(final Book book) {
        return exists(book.getAuthorId(), book.getSearchTitle());
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
    public int getId(final Book book) {
        // TODO implement
        return -1;
    }
}
