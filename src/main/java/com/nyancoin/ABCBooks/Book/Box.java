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

// Behavior / interface, with data-only class BoxEntity
@Service // tell Spring Book is something it should know about as a service: so we get the autowire and can be autowired.
public class Box {
	@Autowired
	private Database database;

	private Integer Add(String box_title, String box_label) {
		return database.AddBox(box_title, box_label);
	}

	public Integer LookupOrAdd(String box_title, String box_label) {
		return database.LookupOrAddBox(box_title, box_label);
	}

	public Iterable<BoxEntity> GetAll() {
		return database.FindAllBoxes();
	}

}