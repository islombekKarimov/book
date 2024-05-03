package com.example.book.service;

import com.example.book.domain.models.AuthorCount;
import com.example.book.domain.models.Book;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllDescSorted();

    Book create(Book book);

    Map<String, List<Book>> getBooksByAuthor();

    List<AuthorCount> findAuthorsByCharacter(char character);
}
