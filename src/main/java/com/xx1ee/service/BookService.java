package com.xx1ee.service;

import com.xx1ee.entity.Book;
import com.xx1ee.entity.Person;
import com.xx1ee.entity.PersonBooks;
import com.xx1ee.repos.BookRepository;
import com.xx1ee.repos.PersonBooksRepository;
import com.xx1ee.repos.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final PersonBooksRepository personBooksRepository;
    @Autowired
    public BookService(BookRepository bookRepository, PersonRepository personRepository, PersonBooksRepository personBooksRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.personBooksRepository = personBooksRepository;
    }

    @Transactional
    public List<Book> getAll() {
        return bookRepository.findAll();
    }
    @Transactional
    public Book getBook(Integer id) {
        return bookRepository.findById(id).get();
    }
    @Transactional
    public PersonBooks getPersonBooks(Integer id) {
        if (bookRepository.findById(id).get().getPersonList().isEmpty()) {
            return null;
        }
        return bookRepository.findById(id).get().getPersonList().get(0);
    }
    @Transactional
    public void createBook(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void releaseBook(Integer bookId, Integer personId) {
        Person person = personRepository.findById(personId).get();
        Book book = bookRepository.findById(bookId).get();
        PersonBooks personBooks = personBooksRepository.findByBook(book);
        personBooksRepository.delete(personBooks);
        person.getBookList().remove(personBooks);
        bookRepository.findById(bookId).get().getPersonList().remove(personBooks);
    }
    @Transactional
    public void createPersonBook(Integer bookId, Person personId) {
        Book book = bookRepository.findById(bookId).get();
        Person person = personRepository.findById(personId.getPerson_id()).get();
        PersonBooks personBooks = personBooksRepository.save(new PersonBooks(book, person, LocalDate.now()));
        person.getBookList().add(personBooks);
        book.getPersonList().add(personBooks);
    }
    @Transactional
    public void deleteBook(Integer id) {
        bookRepository.delete(bookRepository.findById(id).get());
    }
    @Transactional
    public void updateBook(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public Page<Book> findAll(Pageable paging) {
        return bookRepository.findAll(paging);
    }
    @Transactional
    public List<Book> findAll(Sort sort) {
        return bookRepository.findAll(sort);
    }
    @Transactional
    public List<Book> findByNameStartingWith(String name) {
        //System.out.println(bookRepository.findByNameIsStartingWith(name).get(0).getPersonList().get(0).getFio());
        return bookRepository.findByNameIsStartingWith(name);}
}
