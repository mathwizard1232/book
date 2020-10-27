package com.nyancoin.ABCBooks.Book.domain;

public class Book {
    private int id;
    private int authorId;
    private String searchTitle;
    private String title;

    public Book() {}

    public Book (final int id, final int authorId, final String searchTitle, final String title) {
        this.id = id;
        this.authorId = authorId;
        this.searchTitle = searchTitle;
        this.title = title;
    }

    public int getId() { return id; }

    public void setId(final int id) { this.id = id; }

    public int getAuthorId() { return authorId; }

    public void setAuthorId(final int authorId) { this.authorId = authorId; }

    public String getSearchTitle() { return searchTitle; }

    public void setSearchTitle(final String searchTitle) { this.searchTitle = searchTitle; }

    public String getTitle() { return title; }

    public void setTitle(final String title) { this.title = title; }
}
