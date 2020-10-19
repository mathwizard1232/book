package com.nyancoin.ABCBooks.Book;

import org.springframework.data.repository.CrudRepository;

import com.nyancoin.ABCBooks.Book.AuthorEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called AuthorRepository
// CRUD refers Create, Read, Update, Delete

// AuthorEntity is the type being stored; Integer is the identity/index variable type.
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {

}
