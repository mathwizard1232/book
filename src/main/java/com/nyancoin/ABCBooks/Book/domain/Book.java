package com.nyancoin.ABCBooks.Book.domain;

public class Book {
    private Long id;
    private Long authorId;
    private String searchTitle;
    private String title;

    public Book() {}

    public Book (final Long id, final Long authorId, final String searchTitle, final String title) {
        this.id = id;
        this.authorId = authorId;
        this.searchTitle = searchTitle;
        this.title = title;
    }

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public Long getAuthorId() { return authorId; }

    public void setAuthorId(final Long authorId) { this.authorId = authorId; }

    public String getSearchTitle() { return searchTitle; }

    public void setSearchTitle(final String searchTitle) { this.searchTitle = searchTitle; }

    public String getTitle() { return title; }

    public void setTitle(final String title) { this.title = title; }
}
