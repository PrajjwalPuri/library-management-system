package com.prajjwal.library_management.controllers;

import com.prajjwal.library_management.entity.IssueRecord;
import com.prajjwal.library_management.services.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping ("/issuerecords")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issueRecordService;

    @PostMapping("/issuethebook/{bookId}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId) {

        return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }
}
