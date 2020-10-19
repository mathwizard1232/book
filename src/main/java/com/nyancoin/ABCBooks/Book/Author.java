package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Database;

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
	private Database database;

	private Integer Add(String search_name) {
		return database.AddAuthor(search_name);
	}

	public Integer LookupOrAdd(String search_name) {
		return database.LookupOrAddAuthor(search_name);
	}

	public Iterable<AuthorEntity> GetAll() {
		return database.GetAllAuthors();
	}

}