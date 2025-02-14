package com.example.bookIssueService.repository;

import com.example.bookIssueService.model.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {
}
