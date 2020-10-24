package com.nyancoin.ABCBooks.Book;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Behavior / interface, with data-only class BoxEntity
@Service // tell Spring LegacyBox is something it should know about as a service: so we get the autowire and can be autowired.
public class LegacyBox {
	@Autowired
	private Database database;

	private Integer Add(String box_title, String box_label) {
		return database.addBox(box_title, box_label);
	}

	// This is overloaded: if given two strings, adding a box with given labels; if given two integers, adding a relationship of book in box by ids.
	public Integer LookupOrAdd(String box_title, String box_label) {
		return database.lookupOrAddBox(box_title, box_label);
	}

	public Integer LookupOrAdd(Integer box, Integer book) {
		// Note that parameter order is reversed here; each order makes most sense in its place.
		return database.addBookToBox(book, box);
	}

	public Iterable<BoxEntity> GetAll() {
		return database.FindAllBoxes();
	}

	public String GetTitleById(Integer box_id) {
		return database.getBoxTitleById(box_id);
	}

}