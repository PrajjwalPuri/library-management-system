package com.prajjwal.library_management.services;

import com.prajjwal.library_management.entity.Book;
import com.prajjwal.library_management.entity.IssueRecord;
import com.prajjwal.library_management.entity.User;
import com.prajjwal.library_management.repository.BookRepository;
import com.prajjwal.library_management.repository.IssueRecordRepository;
import com.prajjwal.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    // Method to save the book -->
    public IssueRecord issueTheBook(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book Not Found"));

        if(book.getQuantity()<=0 || !book.getIsAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));

        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturn(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity()-1);

        if(book.getQuantity()==0) {
            book.setIsAvailable(false);
        }

        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);
    }


    // method to return the book ---->

    public IssueRecord returnTheBook(Long issueId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue Record not found"));

        if(issueRecord.getIsReturn()) {
            throw new RuntimeException("Book is already returned");
        }

        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity()+1);
        book.setIsAvailable(true);
        bookRepository.save(book);

        issueRecord.setReturnDate(LocalDate.now());
        issueRecord.setIsReturn(true);
        IssueRecord save = issueRecordRepository.save(issueRecord);
        return save;
    }
}
