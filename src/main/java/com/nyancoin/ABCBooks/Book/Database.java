package com.nyancoin.ABCBooks.Book;

import com.nyancoin.ABCBooks.Book.AuthorEntity;
import com.nyancoin.ABCBooks.Book.AuthorRepository;
import com.nyancoin.ABCBooks.Book.Author;

// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Component;


// Route all persistence layer connections through this class to keep implementation details away from all other code.
@Component
public class Database {
	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private BookRepository bookRepo;

	// Using entity manager to get native SQL capabilities in Spring Boot per tutorial:
	// https://www.firstfewlines.com/post/spring-boot-jpa-run-native-sql-query/
	@Autowired
    private EntityManagerFactory entityManagerFactory;

	public Integer AddBook(Integer author_id, String search_title) {
		BookEntity entity = new BookEntity();
		entity.search_title = search_title;
		entity.author = author_id;
		bookRepo.save(entity);
		return entity.book_id;
	}

	public Integer LookupOrAddBook(Integer author_id, String search_title) {
		EntityManager session = entityManagerFactory.createEntityManager();

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

	public Integer AddAuthor(String search_name) {
		AuthorEntity entity = new AuthorEntity();
		entity.search_name = search_name;
		authorRepo.save(entity);
		return entity.author_id;
	}

	public Integer LookupOrAddAuthor(String search_name) {
		EntityManager session = entityManagerFactory.createEntityManager();

		try {
			Integer id = (Integer)session.createNativeQuery("Select author_id FROM authors WHERE search_name=:search_name")
                    .setParameter("search_name", search_name)
                    .getSingleResult();

            return id;
        }
        catch (NoResultException e){
            return AddAuthor(search_name);
        }
        finally {
            if(session.isOpen()) session.close();
        }
	}

	public Iterable<AuthorEntity> GetAllAuthors() {
		return authorRepo.findAll();
	}


}