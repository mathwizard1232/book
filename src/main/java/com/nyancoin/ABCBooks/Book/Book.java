package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Author;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

// Behavior / interface, with data-only class BookEntity
@Service // tell Spring Book is something it should know about as a service: so we get the autowire and can be autowired.
public class Book {
	@Autowired
	private BookRepository repo;

	@Autowired
    private EntityManagerFactory entityManagerFactory;

	private Integer Add(Integer author_id, String search_title) {
		BookEntity entity = new BookEntity();
		entity.search_title = search_title;
		entity.author = author_id;
		repo.save(entity);
		return entity.book_id;
	}

	public Integer LookupOrAdd(Integer author_id, String search_title) {
		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select book_id FROM books WHERE author=:author_id AND search_title=:search_title")
                    .setParameter("search_title", search_title)
					.setParameter("author_id", author_id)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return Add(author_id, search_title);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterable<BookEntity> GetAll() {
		return repo.findAll();
	}

}