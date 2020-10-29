package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Author;
import com.nyancoin.ABCBooks.Book.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// It's your dao, you can decide whether it has responsibility for both the book and book_contents tables
// In which case you can delete BookContentsDao
public class BookDaoImpl implements CrudDao<Book> {
    private static Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    private final NamedParameterJdbcTemplate template;
//    private final RowMapper<Book> bookMapper;

    // TODO create a mapper that fits this signature
    public BookDaoImpl(final NamedParameterJdbcTemplate template) {//}, final RowMapper<Book> bookMapper) {
        this.template = template;
 //       this.bookMapper = bookMapper;
    }

    @Override
    public int add(final Book book) {
        final String sql = " INSERT INTO BOOKS (search_title, author, full_title)" +
                " VALUES(:title, :author, :fullTitle)";

        final SqlParameterSource params = new MapSqlParameterSource("title", book.getSearchTitle())
                .addValue("author", book.getAuthorId())
                .addValue("fullTitle", book.getTitle());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        // INSERT, DELETE, UPDATE are all done through the template's update method
        template.update(sql, params, keyHolder);

        // NPE should never occur here, failures to insert would throw an exception prior to this line
        return keyHolder.getKey().intValue();
    }

    public int add(final String searchTitle, final int authorId) {
        final Book book = new Book();
        book.setAuthorId(authorId);
        book.setSearchTitle(searchTitle);
        return add(book);
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

    public int getId(final String title, final int authorId) {
        final String sql = " SELECT book_id FROM BOOKS WHERE author = :aid AND search_title = :title";

        final SqlParameterSource params = new MapSqlParameterSource("aid", authorId).addValue("title", title);

        try {
            return template.queryForObject(sql, params, Integer.class);
        } catch (final IncorrectResultSizeDataAccessException dae) {
            logger.warn("No existing entry found for book {} with author {}", title,authorId);
            return -1;
        }
    }

    @Override
    public int getId(final Book book) {
        return getId(book.getSearchTitle(), book.getAuthorId());
    }
}
