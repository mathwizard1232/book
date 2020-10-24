package com.nyancoin.ABCBooks.Book.domain;

public class User {

    private Long id;
    private String email;
    private String name;

    public User() {}

    public User(final Long id, final String name, final String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(final String email) { this.email = email; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }
}
