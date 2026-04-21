package com.prajjwal.library_management.repository;

import com.prajjwal.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query ("select ir.book from IssueRecord ir where ir.user.id = :id")
    List<Book> getBookById(@Param("id") Long id);
}
