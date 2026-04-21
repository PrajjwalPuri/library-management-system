package com.prajjwal.library_management.repository;

import com.prajjwal.library_management.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {

}
