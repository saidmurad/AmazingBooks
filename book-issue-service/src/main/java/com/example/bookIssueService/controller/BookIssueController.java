package com.example.bookIssueService.controller;

import com.example.bookIssueService.dto.BookDetail;
import com.example.bookIssueService.model.BookIssue;
import com.example.bookIssueService.model.Customer;
import com.example.bookIssueService.repository.CustomerRepository;
import com.example.bookIssueService.service.BookIssueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookIssues")
public class BookIssueController {

    @Autowired
    private BookIssueService bookIssueService;
    @Autowired
    private CustomerRepository customerRepository;

//    @GetMapping
//    public List<BookIssue> getAllBooks() {
//        return bookIssueService.getAllBooks();
//    }

    @GetMapping("/{id}")
    public BookIssue getBookIssue(@PathVariable Long id) {
        return bookIssueService.getBookIssue(id);
    }


//    @PostMapping
//    public BookIssue addBookIssue(@RequestBody BookIssue bookIssue) {
//        return bookIssueService.add(bookIssue);
//    }

    @PostMapping
    public BookIssue IssueBook(@RequestParam String isbn, @RequestParam String customerId) throws JsonProcessingException {
        return bookIssueService.IssueBook(isbn, customerId);
    }

    @DeleteMapping("/{id}")
    public void deleteBookIssue(@PathVariable Long id) {
        bookIssueService.delete(id);
    }

    @PostMapping("/customer/")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/bookDetail/{isbn}")
    public BookDetail getBookDetail(@PathVariable String isbn) {
        return bookIssueService.getBookDetail(isbn);
    }
}
