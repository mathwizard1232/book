package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// I left a non-generic interface, AuthorDao, in place for reference
public class AuthorDaoImpl implements CrudDao<Author> {
    private static Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

    private NamedParameterJdbcTemplate template;
    // Spring auto-detects that AuthorDao is the only implementation of this interface for wiring
    private RowMapper<Author> authorMapper;

    @Autowired
    public AuthorDaoImpl(final NamedParameterJdbcTemplate template, final RowMapper<Author> authorMapper) {
        this.template = template;
        this.authorMapper = authorMapper;
    }


    // Alternately you could return the full Author object at this point
    @Override
    public Long add(final Author author) {
        final String sql = " INSERT INTO AUTHORS (display_name, search_name)" +
                           " VALUES(:displayName, :searchName)";

        final SqlParameterSource params = new MapSqlParameterSource("displayName", author.getName())
                                                           .addValue("searchName", author.getSearchName());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        // INSERT, DELETE, UPDATE are all done through the template's update metho
        template.update(sql, params, keyHolder);

        // NPE should never occur here, failures to insert would throw an exception prior to this line
        return keyHolder.getKey().longValue();
    }

    @Override
    public void delete(final Long id) {
        final String sql = " DELETE" +
                           " FROM AUTHORS" +
                           " WHERE id = :id";

        final SqlParameterSource params = new MapSqlParameterSource("id", id);
        template.update(sql, params);
    }

    @Override
    public boolean exists(final Author author) {
        // search_name is what we expect to match
        // display_name is the formatted name with titles, etc.
        final String sql = " SELECT 1" +
                           " FROM AUTHORS" +
                           " WHERE search_name = :name" +
                           " LIMIT 1"; // FETCH FIRST 1 ROWS ONLY depending on your RDMBS of choice

        final SqlParameterSource params = new MapSqlParameterSource("name", author.getSearchName());
        return !template.queryForList(sql, params, Integer.class).isEmpty();
    }

    @Override
    public Author get(final Long id) {
        final String sql = " SELECT :id, display_name AS name, search_name" +
                           " FROM AUTHORS" +
                           " WHERE id = :id";

        final SqlParameterSource params = new MapSqlParameterSource("id", id);

        try {
            return template.queryForObject(sql, params, authorMapper);
        } catch (final IncorrectResultSizeDataAccessException dae) {
            // Technically the above exception can be thrown by queryForObject when 2+ results are found
            // But a correct unique constraint on the table prevents this
            logger.warn("Could not find an author entry for id {}", id);
            return null;
        }
    }

    @Override
    public List<Author> getAll() {
        final String sql = " SELECT id, display_name AS name, search_name" +
                           " FROM AUTHORS";

        // I find using the legacy API method preferable to passing an empty params object to queryForList
        // Beyond that their functionality is equivalent
        return template.getJdbcOperations().query(sql, authorMapper);
    }

    @Override
    public Long getId(final Author author) {
        final String sql = " SELECT author_id" +
                           " FROM AUTHORS" +
                           " WHERE display_name = :name";

        final SqlParameterSource params = new MapSqlParameterSource("name", author.getName());
        try {
            return template.queryForObject(sql, params, Long.class);
        } catch (final IncorrectResultSizeDataAccessException dae) {
            logger.warn("No existing entry found for author {}", author.getName());
            return null;
        }
    }
}
