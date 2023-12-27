package com.xx1ee.repos;

import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    private final SessionFactory sessionFactory;
    private Session session;
    @Autowired
    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public List<Person> findAll() {
        session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT id, fio, birth FROM \"Person\"", Person.class).getResultList();
    }
    @Transactional
    public Person findById(Integer id) {
        session = sessionFactory.getCurrentSession();
        System.out.println("jopa");
        Person person = session.find(Person.class, id);
        System.out.println(person.getId());
        return session.find(Person.class, id);
    }
    @Transactional
    public Person findByFio(String fio) {
        session = sessionFactory.getCurrentSession();
        try {
            return session.createNativeQuery("SELECT id, fio, birth FROM \"Person\" where fio = '"+fio+"'", Person.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Transactional
    public List<Book> findBookOfPerson(Integer id) {
        session = sessionFactory.getCurrentSession();
        System.out.println("findbook");
        return session.createNativeQuery("select b.book_id, b.author, b.name, b.year from \"Book\" b join \"PersonBooks\" pb on b.book_id = pb.book_id where pb.person_id = "+ id, Book.class).getResultList();
    }
    @Transactional
    public void createPerson(Person person) {
        session = sessionFactory.getCurrentSession();
        session.persist(person);
    }
    @Transactional
    public void deletePerson(Integer id) {
        session = sessionFactory.getCurrentSession();
        session.detach(findById(id));
    }
    @Transactional
    public void updatePerson(Person person) {
        session = sessionFactory.getCurrentSession();
        session.merge(person);
    }
}
