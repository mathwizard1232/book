package com.nyancoin.ABCBooks.Book;

import javax.persistence.*;

// Data-only representation to mirror database

@Entity // This tells Hibernate to make a mapping from this class to database
@Table(name = "boxes") // because table name is not box_entity, specify manually; (using "LegacyBox" in Java for the Service class vs this data-only one)
public class BoxEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer box_id;
	
	String box_title; // Starting in database at 50 characters cap; intended to be quick to type and easy to match
	String box_label; // Starting in database at 200 characters cap; intended to be a fuller description of contents / location
}