package com.example.bookService.controller;

import com.example.bookService.model.Book;
import com.example.bookService.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }

    @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookService.getBook(isbn);
    }


    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.add(book);
    }

    @PutMapping
    public Book updatePost( @RequestBody Book updatedBook) {
        return bookService.edit(updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
