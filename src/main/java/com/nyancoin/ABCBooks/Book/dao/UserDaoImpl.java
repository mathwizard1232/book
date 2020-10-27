package com.nyancoin.ABCBooks.Book.dao;

import com.nyancoin.ABCBooks.Book.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements CrudDao<User> {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public UserDaoImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int add(final User user) {
        final String sql = " INSERT INTO USERS VALUES (:email, :name)";

        final SqlParameterSource params = new MapSqlParameterSource("email", user.getEmail())
                                                          .addValue("name", user.getName());
        final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, params, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(final Long id) {
        // TODO implement
        return;
    }

    @Override
    public boolean exists(final User user) {
        // TODO implement
        return false;
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

    @Override
    public int getId(final User user) {
        // TODO implement
        return -1;
    }
}
