package com.nyancoin.ABCBooks.Book;

import org.springframework.data.repository.CrudRepository;

import com.nyancoin.ABCBooks.Book.BookEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called BookRepository
// CRUD refers Create, Read, Update, Delete

// BookEntity is the type being stored; Integer is the identity/index variable type.
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

}
