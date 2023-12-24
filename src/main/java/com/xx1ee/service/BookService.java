package com.xx1ee.service;

import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import com.xx1ee.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
    public Book getBook(Integer id) {
        return bookRepository.findById(id);
    }
    public Person getPersonBooks(Integer id) {
        return bookRepository.findPersonOfBook(id);
    }
    public void createBook(Book book) {
        bookRepository.createBook(book);
    }
    public void releaseBook(Book book) {
        bookRepository.releaseBook(book);
    }
    public void createPersonBook(Integer id, Person person) {
        bookRepository.createPersonBook(id, person.getId());
    }
}
