package com.xx1ee.repos;

import com.xx1ee.classes.PersonBooksPK;
import com.xx1ee.config.SpringConfig;
import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import com.xx1ee.model.PersonBooks;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final SessionFactory sessionFactory;
    private Session session;
    @Autowired
    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> findAll() {
        session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT book_id, name, author, year FROM \"Book\"", Book.class).getResultList();
    }
    @Transactional
    public Book findById(Integer id) {
        session = sessionFactory.getCurrentSession();
        return session.find(Book.class, id);
    }
    @Transactional
    public Person findPersonOfBook(Integer id) {
        session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT person_id FROM \"PersonBooks\" where book_id = " + id, Person.class).getSingleResult();
    }
    @Transactional
    public void createBook(Book book) {
        session = sessionFactory.getCurrentSession();
        session.persist(book);
    }
    @Transactional
    public void releaseBook(Book book) {
        session = sessionFactory.getCurrentSession();
        session.createNativeQuery("DELETE FROM \"PersonBooks\" WHERE book_id = " + book.getBook_id()).executeUpdate();
    }
    @Transactional
    public void createPersonBook(Integer bookId, Integer personId) {
        session = sessionFactory.getCurrentSession();
        session.persist(new PersonBooks(new PersonBooksPK(bookId, personId)));
    }
    @Transactional
    public void deleteBook(Integer id) {
        session = sessionFactory.getCurrentSession();
        session.detach(findById(id));
    }
    @Transactional
    public void updateBook(Book book) {
        session = sessionFactory.getCurrentSession();
        session.merge(book);
    }
}
