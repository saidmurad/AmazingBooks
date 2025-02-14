package com.example.bookService.service;

import com.example.bookService.model.Book;
import com.example.bookService.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book getBook(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public Book edit(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
         bookRepository.deleteById(id);
    }

}
