package com.prajjwal.library_management.controllers;

import com.prajjwal.library_management.DTO.BookDTO;
import com.prajjwal.library_management.entity.Book;
import com.prajjwal.library_management.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getallbooks")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/allmybooks")
    public ResponseEntity<List<Book>> getUserBooks() {
        return ResponseEntity.ok(bookService.getmybook());
    }

    @GetMapping("/getbookbyid/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping ("/addbook")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook (@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @PutMapping("/updatebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
