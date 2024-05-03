package com.example.book.service;

import com.example.book.domain.models.AuthorCount;
import com.example.book.domain.models.Book;
import com.example.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllDescSorted() {
        return bookRepository.getAllListDescSorted();
    }

    @Override
    public Book create(Book book) {
      return bookRepository.create(book);
    }

    @Override
    public Map<String, List<Book>> getBooksByAuthor() {
        List<Book> getList = bookRepository.getAll();
        Map<String, List<Book>> booksByAuthor = getList.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        return booksByAuthor;
    }

    @Override
    public List<AuthorCount> findAuthorsByCharacter(char character) {
        List<Book> getList = bookRepository.getAll();
        Map<String, Integer> authorCounts = getList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(Character.toString(character).toLowerCase()))
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.summingInt(book -> countCharacterOccurrences(book.getTitle(), character))));

        return authorCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .map(entry -> new AuthorCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
}

    private int countCharacterOccurrences(String text, char character) {
        return (int) text.chars()
                .map(Character::toLowerCase)
                .filter(ch -> ch == Character.toLowerCase(character))
                .count();
    }
}
