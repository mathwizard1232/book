package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Author;
import com.nyancoin.ABCBooks.Book.Database;

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
	private Database database;

	private Integer Add(Integer author_id, String search_title) {
		return database.AddBook(author_id, search_title);
	}

	public Integer LookupOrAdd(Integer author_id, String search_title) {
		return database.LookupOrAddBook(author_id, search_title);
	}

	public Iterable<BookEntity> GetAll() {
		return database.FindAllBooks();
	}

}