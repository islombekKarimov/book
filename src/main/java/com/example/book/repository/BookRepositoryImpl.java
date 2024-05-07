package com.example.book.repository;

import com.example.book.domain.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{
    private final JdbcTemplate jdbcTemplate;
    private final BeanPropertyRowMapper<Book> bookRowMapper = new BeanPropertyRowMapper<>(Book.class);

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllListDescSorted(){
        String sql = " SELECT * FROM book ORDER BY title desc ";
        return jdbcTemplate.query(sql, bookRowMapper);
    }

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO book (title, author, description) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getDescription());
        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, bookRowMapper);
    }

}
