package com.vesspace.SpringSecurityTest.service;

import com.github.javafaker.Faker;
import com.vesspace.SpringSecurityTest.model.MyUser;
import com.vesspace.SpringSecurityTest.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import com.vesspace.SpringSecurityTest.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class BookService {

    private List<Book> books;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadPetInDB() {
        Faker faker = new Faker();
        books = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Book.builder()
                        .id(i)
                        .title(faker.book().title())
                        .author(faker.book().author())
                        .genre(faker.book().genre())
                        .publisher(faker.book().publisher())
                        .build())
                .toList();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
