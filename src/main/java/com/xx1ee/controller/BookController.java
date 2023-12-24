package com.xx1ee.controller;

import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import com.xx1ee.service.BookService;
import com.xx1ee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    PersonService personService;
    @GetMapping("/books")
    public String getAllPerson(Model model) {
        model.addAttribute("allBooks", bookService.getAll());
        return "allBooks";
    }
    @GetMapping("/books/{id}")
    public String getPerson(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        System.out.println(bookService.getPersonBooks(id));
        model.addAttribute("personBooks", bookService.getPersonBooks(id));
        model.addAttribute("allPersons", personService.getAll());
        model.addAttribute("person", new Person());
        return "findBookById";
    }
    @GetMapping("/books/new")
    public String addPerson(Model model) {
        model.addAttribute("book", new Book());
        return "createBook";
    }
    @PostMapping("/books/create")
    public String createPerson(@ModelAttribute("book") Book book) {
        bookService.createBook(book);
        return "redirect:/books";
    }
    @GetMapping("/books/release/{id}")
    public String releaseBook(@PathVariable("id") Integer id) {
        System.out.println(bookService.getBook(id).getName());
        bookService.releaseBook(bookService.getBook(id));
        return "redirect:/books/" + id;
    }
    @PostMapping("/books/appoint/{id}")
    public String appointBook(@PathVariable("id") Integer id, @ModelAttribute("person") Person person) {
        bookService.createPersonBook(id, person);
        return "redirect:/books/" + id;
    }
}
