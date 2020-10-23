package com.nyancoin.ABCBooks.Book.domain;

public class Author {

    private Long id;
    private String name;
    private String searchName;

    public Author() {}

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getName() { return name;}

    public void setName(final String name) { this.name = name; }

    public String getSearchName() { return searchName; }

    public void setSearchName(final String searchName) { this.searchName = searchName; }
}
