package com.xx1ee.controller;

import com.xx1ee.entity.Book;
import com.xx1ee.entity.Person;
import com.xx1ee.service.BookService;
import com.xx1ee.service.PersonService;
import com.xx1ee.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {
    BookService bookService;
    PersonService personService;
    BookValidator bookValidator;
    @Autowired
    public BookController(BookService bookService, PersonService personService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.personService = personService;
        this.bookValidator = bookValidator;
    }

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
    public String createPerson(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "createBook";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }
    @PostMapping("/books/release/{id}")
    public String releaseBook(@PathVariable("id") Integer id, @ModelAttribute("personBooks") Person person) {
        System.out.println(id + " " + person.getPerson_id());
        bookService.releaseBook(id, person.getPerson_id());
        return "redirect:/books/" + id;
    }
    @PostMapping("/books/appoint/{id}")
    public String appointBook(@PathVariable("id") Integer id, @ModelAttribute("person") Person person) {
        bookService.createPersonBook(id, person);
        return "redirect:/books/" + id;
    }
    @DeleteMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getBook(id));
        return "updateBook";
    }
    @PostMapping("/books/updateMethod")
    public String updateBookMethod(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }
    @GetMapping("/books/{page}/{pageSize}")
    public String getBooks(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize, Model model) {
        model.addAttribute("allBooks", bookService.findAll(PageRequest.of(page, pageSize)).getContent());
        return "allBooks";
    }
    @GetMapping("/books/sort_by_year")
    public String getBooks(Model model) {
        model.addAttribute("allBooks", bookService.findAll(Sort.by("year")));
        return "allBooks";
    }
    @GetMapping("/books/{page}/{pageSize}/sort_by_year")
    public String getCombo(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize, Model model) {
        model.addAttribute("allBooks", bookService.findAll(PageRequest.of(page, pageSize, Sort.by("year"))));
        return "allBooks";
    }
}
