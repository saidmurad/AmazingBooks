package com.example.bookIssueService.service;

import com.example.bookIssueService.dto.BookDetail;
import com.example.bookIssueService.model.Book;
import com.example.bookIssueService.model.BookIssue;
import com.example.bookIssueService.model.Customer;
import com.example.bookIssueService.repository.BookIssueRepository;
import com.example.bookIssueService.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class BookIssueService {

    private final BookIssueRepository bookIssueRepository;
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public BookIssueService(BookIssueRepository bookIssueRepository, CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.bookIssueRepository = bookIssueRepository;
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }
    public BookIssue getBookIssue(Long id) {
        return bookIssueRepository.findById(id).orElseThrow(() -> new RuntimeException("BookIssue not found"));
    }

    public BookIssue add(BookIssue bookIssue) {
        return bookIssueRepository.save(bookIssue);
    }

    public BookIssue IssueBook(String isbn, String customerId) throws JsonProcessingException {
        // request requestedBook-service to fetch requestedBook and update the requestedBook
        String URI_BOOK = "http://localhost:8081/books";
        Book requestedBook = restTemplate.getForObject(URI_BOOK + "/" + isbn, Book.class);
        requestedBook.setIssuedCopies(requestedBook.getIssuedCopies() + 1);

        // make update request to requestedBook object
//        restTemplate.postForEntity("http://localhost:8080/RestApi", requestedBook, Book.class, "");
        //restTemplate.put(URI_BOOK, requestedBook, "");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestedBook);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(URI_BOOK, HttpMethod.PUT, requestEntity, String.class);


        Customer customer = customerRepository.findById(Long.parseLong(customerId)).orElseThrow(() -> new RuntimeException("Customer not found"));
        BookIssue bookIssue = new BookIssue();
        bookIssue.setIsbn(requestedBook.getIsbn());
        bookIssue.setNoOfCopies(requestedBook.getTotalCopies() - requestedBook.getIssuedCopies());
        bookIssue.setCustomer(customer);
        return bookIssueRepository.save(bookIssue);
    }

    public BookIssue edit(BookIssue bookIssue) {
        return bookIssueRepository.save(bookIssue);
    }

    public BookDetail getBookDetail(String isbn) {
        return null;
    }

    public void delete(Long id) {
         bookIssueRepository.deleteById(id);
    }

}
