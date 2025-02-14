package com.example.bookService.controller;

import com.example.bookService.model.Author;
import com.example.bookService.model.Book;
import com.example.bookService.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

//    @GetMapping
//    public List<Book> getAllBooks() {
//        return bookService.getAllBooks();
//    }

    @GetMapping("/{isbn}")
    public Author getBook(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }


    @PostMapping
    public Author addBook(@RequestBody Author author) {
        return authorService.add(author);
    }

    @PutMapping
    public Author updatePost( @RequestBody Author updatedAuthor) {
        return authorService.edit(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        authorService.delete(id);
    }
}
