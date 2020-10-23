package com.nyancoin.ABCBooks.Book;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
//import javax.persistence.Query;
import java.util.Iterator;

//import org.hibernate.SQLQuery;

import org.springframework.stereotype.Component;


// Route all persistence layer connections through this class to keep implementation details away from all other code.
@Component
public class Database {
	private AuthorRepository authorRepo;
	private BookRepository bookRepo;
	private BoxContentsRepository boxContentsRepo;
	private BoxRepository boxRepo;
	// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
	// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
	// Grabbing en EntityManagerFactory is "a Java native thing" whereas this is Hibernate's version
	private EntityManager session;

	@Autowired
	public Database(final AuthorRepository authorRepository, final BookRepository bookRepository,
					final BoxContentsRepository boxContentsRepository, final BoxRepository boxRepository,
					final EntityManager session) {
		this.authorRepo = authorRepository;
		this.bookRepo = bookRepository;
		this.boxContentsRepo = boxContentsRepository;
		this.boxRepo = boxRepository;
		this.session = session;
	}

	public Integer addBookToBox(final Integer book, final Integer box) {
		final BoxContentsEntity entity = new BoxContentsEntity();
		entity.book = book;
		entity.box = box;
		boxContentsRepo.save(entity);
		return entity.box_contents_id;
	}

	public Integer addBox(final String boxTitle, final String boxLabel) {
		BoxEntity entity = new BoxEntity();
		entity.box_title = boxTitle;
		entity.box_label = boxLabel;
		boxRepo.save(entity);
		return entity.box_id;
	}

	public Integer lookupOrAddBox(final String boxTitle, final String boxLabel) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select box_id FROM boxes WHERE box_title=:box_title")
                    .setParameter("box_title", boxTitle)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return addBox(boxTitle, boxLabel);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public String getBoxTitleById(final Integer boxId) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			String id = (String)session.createNativeQuery("Select box_title FROM boxes WHERE box_id=:box_id")
                    .setParameter("box_id", boxId)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return "Box not found in getBoxTitleById. id:" + boxId + "\n<br><br>";
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterable<BoxEntity> FindAllBoxes() {
		return boxRepo.findAll();
	}

	public Integer AddBook(Integer author_id, String search_title) {
		BookEntity entity = new BookEntity();
		entity.search_title = search_title;
		entity.author = author_id;
		bookRepo.save(entity);
		return entity.book_id;
	}

	public Integer LookupOrAddBook(Integer author_id, String search_title) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select book_id FROM books WHERE author=:author_id AND search_title=:search_title")
                    .setParameter("search_title", search_title)
					.setParameter("author_id", author_id)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return AddBook(author_id, search_title);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterable<BookEntity> FindAllBooks() {
		return bookRepo.findAll();
	}

	public Integer addAuthor(final String author) {
		final LegacyAuthorEntity entity = new LegacyAuthorEntity();
		entity.search_name = author;
		authorRepo.save(entity);
		return entity.author_id;
	}

	public Integer LookupOrAddAuthor(String search_name) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select author_id FROM authors WHERE search_name=:search_name")
                    .setParameter("search_name", search_name)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return addAuthor(search_name);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterator<LegacyAuthorEntity> GetAllAuthors() {
		//return authorRepo.findAll();
//		EntityManager session = entityManagerFactory.createEntityManager();
		// SQLQuery query = session.createQuery("SELECT * FROM authors ORDER BY search_name ASC").addEntity(LegacyAuthorEntity.class); // this tells Hibernate how to cast results
        // List<LegacyAuthorEntity> users = query.getResultList();
		// return users.iterator(); // just testing this other query style; will switch to returning a list
	}
}