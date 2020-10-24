package com.nyancoin.ABCBooks.Book.domain;

import java.util.List;

public class Box {

    private Long id;
    private String label;
    private String title;
    private List<Long> bookIds;

    public Box() {}

    public Box(final Long id, final String label, final String title, final List<Long> bookIds) {
        this.id = id;
        this.label = label;
        this.title = title;
        this.bookIds = bookIds;
    }

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getLabel() { return label; }

    public void setLabel(final String label) { this.label = label; }

    public String getTitle() { return title; }

    public void setTitle(final String title) { this.title = title; }

    public List<Long> getBookIds() { return bookIds;}

    public void setBookIds(final List<Long> bookIds) { this.bookIds = bookIds; }
}
