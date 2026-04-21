package com.prajjwal.library_management.services;

import com.prajjwal.library_management.DTO.BookDTO;
import com.prajjwal.library_management.entity.Book;

import com.prajjwal.library_management.entity.User;
import com.prajjwal.library_management.repository.BookRepository;
import com.prajjwal.library_management.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getmybook() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = user.getId();

        List<Book> books = bookRepository.getBookById(userId);
        return books;
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return book;
    }

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setIsAvailable(bookDTO.getIsAvailable());

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));

        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setQuantity(bookDTO.getQuantity());
        oldBook.setIsAvailable(bookDTO.getIsAvailable());

        return bookRepository.save(oldBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
