package com.vesspace.SpringSecurityTest.controller;

import com.vesspace.SpringSecurityTest.model.MyUser;
import lombok.AllArgsConstructor;
import com.vesspace.SpringSecurityTest.model.Book;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.vesspace.SpringSecurityTest.service.BookService;

import java.util.List;

@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to unprotected page!";
    }

    @GetMapping("/all-books")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody MyUser user) {
        bookService.addUser(user);
        return "User is saved!";
    }
}
