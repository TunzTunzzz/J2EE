package com.example.bai1.controller;

import com.example.bai1.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private static final List<Book> books = new ArrayList<>();

    // Initialize mock data
    static {
        books.add(new Book(1, "Java Spring Boot", "Huy Cuong", 150.0));
        books.add(new Book(2, "J2EE Patterns", "Nam Cuong", 200.0));
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", books);
        return "list-books"; // We'll create a list view too for completeness
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        // Simple mock ID generation
        int newId = books.stream().mapToInt(Book::getId).max().orElse(0) + 1;
        book.setId(newId);
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "edit-book";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        // Find and replace
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                break;
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        books.removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }
}
