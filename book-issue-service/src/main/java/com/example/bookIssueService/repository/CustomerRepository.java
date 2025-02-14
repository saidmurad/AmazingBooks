package com.example.bookIssueService.repository;

import com.example.bookIssueService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
