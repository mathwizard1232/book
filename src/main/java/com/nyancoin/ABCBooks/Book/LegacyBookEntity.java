package com.nyancoin.ABCBooks.Book;

import javax.persistence.*;

// Data-only representation to mirror database

//@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Books") // because table name is not author_entity, specify manually; (using "LegacyAuthor" in Java for the Service class vs this data-only one
public class LegacyBookEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer book_id;

	Integer author; // corresponds to an author id
	
	String search_title; // Starting in database at 50 characters cap; intended to be quick to type and easy to match
	String full_title; // Starting in database at 200 characters cap; intended to be a proper full form for display
}