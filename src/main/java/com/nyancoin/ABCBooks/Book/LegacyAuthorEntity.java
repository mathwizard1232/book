package com.nyancoin.ABCBooks.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Data-only representation to mirror database

//@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Authors") // because table name is not author_entity, specify manually; (using "LegacyAuthor" in Java for the Service class vs this data-only one
public class LegacyAuthorEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer author_id;
	
	String search_name; // Starting in database at 50 characters cap; intended to be quick to type and easy to match
	String display_name; // Starting in database at 200 characters cap; intended to be a proper full form for display
}