package com.example.book.domain.models;

public class AuthorCount {
    private String author;

    private int count;

    public AuthorCount(String author, int count) {
        this.author = author;
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
