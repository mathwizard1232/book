package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

// Behavior / interface, with data-only class below^W in separate AuthorEntity file to comply with Java standards
@Service // ask Spring for service to wire up the autowire below
public class Author {
	@Autowired
	private AuthorRepository repo;

	@Autowired
    private EntityManagerFactory entityManagerFactory;

	private Integer Add(String search_name) {
		AuthorEntity entity = new AuthorEntity();
		entity.search_name = search_name;
		repo.save(entity);
		return entity.author_id;
	}

	public Integer LookupOrAdd(String search_name) {
		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select author_id FROM authors WHERE search_name=:search_name")
                    .setParameter("search_name", search_name)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return Add(search_name);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterable<AuthorEntity> GetAll() {
		return repo.findAll();
	}

}