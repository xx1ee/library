package com.xx1ee.service;

import com.xx1ee.model.Book;
import com.xx1ee.model.Person;
import com.xx1ee.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    public List<Person> getAll() {
        return personRepository.findAll();
    }
    public Person getPerson(Integer id) {
        return personRepository.findById(id);
    }
    public List<Book> getPersonBooks(Integer id) {
        return personRepository.findBookOfPerson(id);
    }
    public void createPerson(Person person) {
        personRepository.createPerson(person);
    }
}
