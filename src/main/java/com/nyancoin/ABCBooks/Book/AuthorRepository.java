package com.nyancoin.ABCBooks.Book;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called AuthorRepository
// CRUD refers Create, Read, Update, Delete

// LegacyAuthorEntity is the type being stored; Integer is the identity/index variable type.
public interface AuthorRepository extends CrudRepository<LegacyAuthorEntity, Integer> {

}
