package com.example.book.repository;

import com.example.book.domain.models.Book;

import java.util.List;


public interface BookRepository {

     List<Book> getAllListDescSorted();

     Book create(Book book);

     List<Book> getAll();

}
