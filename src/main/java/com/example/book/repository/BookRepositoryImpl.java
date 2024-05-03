package com.example.book.repository;

import com.example.book.domain.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllListDescSorted(){
        String sql = " SELECT * FROM book ORDER BY title desc ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO book (id, title, author, description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getAuthor(), book.getDescription());
        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

}
