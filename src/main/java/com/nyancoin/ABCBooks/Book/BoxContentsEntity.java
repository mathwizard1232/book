package com.nyancoin.ABCBooks.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Data-only representation to mirror database

@Entity // This tells Hibernate to make a mapping from this class to database
@Table(name = "box_contents")
public class BoxContentsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer box_contents_id;
	
	Integer box; // box id of the box which contains the Book
	Integer book; // a book contained in the box
}