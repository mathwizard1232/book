package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Database;
import com.nyancoin.ABCBooks.Book.UIO;

import java.util.Iterator;

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

	private UIO UIO;

	public Author() {
		UIO = new UIO();
	}

	private Integer Add(String search_name) {
		return database.AddAuthor(search_name);
	}

	public Integer LookupOrAdd(String search_name) {
		return database.LookupOrAddAuthor(search_name);
	}

	public Iterator<AuthorEntity> GetAll() {
		return database.GetAllAuthors();
	}

	// Get the display name, if there's one set, otherwise just use the search name
	public String DisplayName(AuthorEntity author) {
		if (author.display_name == null || author.display_name.isEmpty()) {
			return author.search_name;
		} else {
			return author.display_name;
		}
	}

	// An HTML representation of all authors recorded
	public String DisplayAuthors() {
		int count = 0;
		Iterator<AuthorEntity> authors = GetAll();
		String list = "";

		list += "<ul>\n";
		AuthorEntity current;
		while (authors.hasNext()) {
			current = (AuthorEntity) authors.next();
			list += "<li>" + DisplayName(current) + "</li>\n";
			count++;
		}
		list += "</ul>\n";

		String result = UIO.h3("There are " + count + " authors recorded." );

		result += list;

		return result;
	}

}