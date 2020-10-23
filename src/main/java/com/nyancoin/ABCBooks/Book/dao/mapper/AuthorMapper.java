package com.nyancoin.ABCBooks.Book.dao.mapper;

import com.nyancoin.ABCBooks.Book.domain.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
// This class is suitable for mapping one (queryForObject) or many (queryForList) Authors, with the assumption that one
// row of results equals one object
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Author author = new Author();

        author.setId(rs.getLong("id"));
        // We use the alias'd column name from the query
        author.setName(rs.getString("name"));
        author.setSearchName(rs.getString("search_name"));

        return author;
    }
}
