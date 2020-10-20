package com.nyancoin.ABCBooks.Book;

import org.springframework.data.repository.CrudRepository;

import com.nyancoin.ABCBooks.Book.BoxEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called BoxRepository
// CRUD refers Create, Read, Update, Delete

// BoxEntity is the type being stored; Integer is the identity/index variable type.
public interface BoxRepository extends CrudRepository<BoxEntity, Integer> {

}
