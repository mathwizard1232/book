package com.nyancoin.ABCBooks.Book;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Behavior / interface, with data-only class below^W in separate LegacyAuthorEntity file to comply with Java standards
@Service // ask Spring for service to wire up the autowire below
public class LegacyAuthor {

	private Database database;
	private UIO uio;

	@Autowired
	public LegacyAuthor(final Database database, final UIO uio) {
		this.database = database;
		this.uio = uio;
	}

	private Integer add(final String search_name) {
		return database.addAuthor(search_name);
	}

	public Integer lookupOrAdd(final String search_name) {
		return database.LookupOrAddAuthor(search_name);
	}

	public Iterator<LegacyAuthorEntity> GetAll() {
		return database.GetAllAuthors();
	}

	// Get the display name, if there's one set, otherwise just use the search name
	public String DisplayName(LegacyAuthorEntity author) {
		if (author.display_name == null || author.display_name.isEmpty()) {
			return author.search_name;
		} else {
			return author.display_name;
		}
	}

	// An HTML representation of all authors recorded
	public String DisplayAuthors() {
		int count = 0;
		Iterator<LegacyAuthorEntity> authors = GetAll();
		String list = "";

		list += "<ul>\n";
		LegacyAuthorEntity current;
		while (authors.hasNext()) {
			current = (LegacyAuthorEntity) authors.next();
			list += "<li>" + DisplayName(current) + "</li>\n";
			count++;
		}
		list += "</ul>\n";

		String result = uio.h3("There are " + count + " authors recorded." );

		result += list;

		return result;
	}

}