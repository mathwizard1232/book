package com.nyancoin.ABCBooks.Book.service;

import com.nyancoin.ABCBooks.Book.dao.AuthorDao;
import com.nyancoin.ABCBooks.Book.dao.CrudDao;
import com.nyancoin.ABCBooks.Book.domain.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
    private CrudDao<Author> dao;

    @Autowired
    public AuthorServiceImpl(final CrudDao<Author> dao) {
        this.dao = dao;
    }

    @Override
    public void delete(final Long id) { dao.delete(id); }

    @Override
    public List<Author> getAll() { return dao.getAll(); }

    @Override
    public Author get(final Long id) { return dao.get(id); }

    @Override
    public Long getOrAdd(final Author author) {
        /*
            Super-short-form with ternary and no logging:
            return dao.exists(author) ? dao.getId(author) : dao.add(author);
        */
        if (dao.exists(author)) {
            // When we call logger.debug and aren't logging at that level, no message is generated, HOWEVER
            // Using slf4j placeholder substitution {}, complex toString methods can still be a performance hit
            // So check whether we're even IN debug mode. If not, we won't even bother to evaluate the toString
            if (logger.isDebugEnabled()) {
                logger.debug("An author entry already exists for author: {}", author);
            }
            final Long id = dao.getId(author);
            // Here there is no complex toString cost, so we don't worry about it. If we're not in debug, no message.
            logger.debug("Retrieved existing id: {}", id);
            return id;
        }

        // I don't prefer attaching an else block. If the condition above is hit, it'll return and we'll be out
        // of the method scope anyway. If not, then what would have been the else is the only branch, seen below.
        if (logger.isDebugEnabled()) {
            logger.debug("No author entry exists for author: {}", author);
        }
        final Long id = dao.add(author);
        logger.debug("Inserted with generated id: {}", id);
        return id;
    }
}
