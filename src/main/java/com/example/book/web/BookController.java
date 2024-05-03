package com.example.book.web;

import com.example.book.domain.models.AuthorCount;
import com.example.book.domain.models.Book;
import com.example.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

   private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllDescSorted(){
        return ResponseEntity.ok(bookService.getAllDescSorted());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @GetMapping("/get-by-author")
    public ResponseEntity<Map<String, List<Book>>>getBooksByAuthor(){
        return ResponseEntity.ok(bookService.getBooksByAuthor());
    }

    @GetMapping("/authors-by-char")
    public ResponseEntity<List<AuthorCount>> getAuthorsByCharacter(@RequestParam("character") char character) {
        return ResponseEntity.ok(bookService.findAuthorsByCharacter(character));
    }

}
