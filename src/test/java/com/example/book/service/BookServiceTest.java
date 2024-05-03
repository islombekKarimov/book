package com.example.book.service;

import com.example.book.domain.models.AuthorCount;
import com.example.book.domain.models.Book;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void testGetAllDescSorted() {
        when(bookRepository.getAllListDescSorted()).thenReturn(List.of(generateBook()));
        List<Book> expectedBooks = List.of(generateBook());
        List<Book> result = bookService.getAllDescSorted();
        Assertions.assertEquals(expectedBooks.get(0).getAuthor(), result.get(0).getAuthor());
        Assertions.assertEquals(expectedBooks.get(0).getTitle(), result.get(0).getTitle());
    }

    @Test
    void testCreate() {
        Book newBook = generateBook();
        Mockito.when(bookRepository.create(newBook)).thenReturn(newBook);
        Book result = bookService.create(newBook);
        Assertions.assertEquals(newBook, result);
    }

    @Test
    void testGetBooksByAuthor() {

        when(bookRepository.getAll()).thenReturn(List.of(generateBook()));

        Map<String, List<Book>> expectedBooksByAuthor = Map.of(
                "Author1", List.of(generateBook()));
        Map<String, List<Book>> result = bookService.getBooksByAuthor();
        System.out.println(expectedBooksByAuthor.get("Author1"));
        System.out.println(result);

        assertEquals(expectedBooksByAuthor.get("Author1").size(), result.get("Author1").size());
    }

    @Test
    void testFindAuthorsByCharacter() {

        when(bookRepository.getAll()).thenReturn(List.of(generateBook()));
        List<AuthorCount> expectedAuthorCounts = Arrays.asList( new AuthorCount("Author1", 2));

        List<AuthorCount> result = bookService.findAuthorsByCharacter('t');

        assertEquals(expectedAuthorCounts.get(0).getCount(), result.get(0).getCount());
        assertEquals(expectedAuthorCounts.get(0).getAuthor(), result.get(0).getAuthor());
    }


    private Book generateBook(){
        Book book = new Book();
        book.setAuthor("Author1");
        book.setTitle("Title1");
        return book;
    }

}
