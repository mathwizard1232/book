package com.nyancoin.ABCBooks.Book;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called BookRepository
// CRUD refers Create, Read, Update, Delete

// LegacyBookEntity is the type being stored; Integer is the identity/index variable type.
public interface BookRepository extends CrudRepository<LegacyBookEntity, Integer> {

}
