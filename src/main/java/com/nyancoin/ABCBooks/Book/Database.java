package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Author;
import com.nyancoin.ABCBooks.Book.BoxContentsEntity;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
//import javax.persistence.Query;
import java.util.List;
import java.util.Iterator;

//import org.hibernate.SQLQuery;
import org.hibernate.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


// Route all persistence layer connections through this class to keep implementation details away from all other code.
@Component
public class Database {
	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BoxRepository boxRepo;

	@Autowired
	private BoxContentsRepository boxContentsRepo;

	// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
	// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
	//@Autowired
    //private EntityManagerFactory entityManagerFactory;

	//@Autowired
	//@Qualifier("SharedEntityManagerCreator#0")
	private EntityManager entityManager;

	public Database(ApplicationContext context) {
	//	entityManager = context.getBean(EntityManager.class);
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Integer AddBookToBox(Integer book, Integer box) {
		BoxContentsEntity entity = new BoxContentsEntity();
		entity.book = book;
		entity.box = box;
		boxContentsRepo.save(entity);
		return entity.box_contents_id;
	}

	public Integer AddBox(String box_title, String box_label) {
		BoxEntity entity = new BoxEntity();
		entity.box_title = box_title;
		entity.box_label = box_label;
		boxRepo.save(entity);
		return entity.box_id;
	}

	public Integer LookupOrAddBox(String box_title, String box_label) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)entityManager.createNativeQuery("Select box_id FROM boxes WHERE box_title=:box_title")
                    .setParameter("box_title", box_title)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return AddBox(box_title, box_label);
        }
        finally {
            if(entityManager.isOpen()) entityManager.close();
        }
	}

	public String GetBoxTitleById(Integer box_id) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			String id = (String)entityManager.createNativeQuery("Select box_title FROM boxes WHERE box_id=:box_id")
                    .setParameter("box_id", box_id)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return "Box not found in GetBoxTitleById. id:" + box_id + "\n<br><br>";
        }
        finally {
            if(entityManager.isOpen()) entityManager.close();
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
			Integer id = (Integer)entityManager.createNativeQuery("Select book_id FROM books WHERE author=:author_id AND search_title=:search_title")
                    .setParameter("search_title", search_title)
					.setParameter("author_id", author_id)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return AddBook(author_id, search_title);
        }
        finally {
            if(entityManager.isOpen()) entityManager.close();
        }
	}

	public Iterable<BookEntity> FindAllBooks() {
		return bookRepo.findAll();
	}

	public Integer AddAuthor(String search_name) {
		AuthorEntity entity = new AuthorEntity();
		entity.search_name = search_name;
		authorRepo.save(entity);
		return entity.author_id;
	}

	public Integer LookupOrAddAuthor(String search_name) {
//		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)entityManager.createNativeQuery("Select author_id FROM authors WHERE search_name=:search_name")
                    .setParameter("search_name", search_name)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return AddAuthor(search_name);
        }
        finally {
            if(entityManager.isOpen()) entityManager.close();
        }
	}

	public Iterator<AuthorEntity> GetAllAuthors() {
		//return authorRepo.findAll();
//		EntityManager session = entityManagerFactory.createEntityManager();
		Session session = sessionFactory.openSession();
		//Transaction tx = null;
		//try {
		//	tx = session.beginTransaction();

		NativeQuery query = session.createNativeQuery("SELECT * FROM authors ORDER BY search_name ASC").addEntity(AuthorEntity.class);//.addEntity(AuthorEntity.class); // this tells Hibernate how to cast results
		List<AuthorEntity> users = query.getResultList();
		//	tx.commit();
		//}
		//catch (Exception e) {
		//	if (tx!=null) tx.rollback();
		//	throw e;
		//}
		//finally {
			session.close();
		//}

		return users.iterator(); // just testing this other query style; will switch to returning a list
	}


}