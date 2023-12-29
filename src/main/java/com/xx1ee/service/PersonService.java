package com.xx1ee.service;

import com.xx1ee.entity.Book;
import com.xx1ee.entity.Person;
import com.xx1ee.repos.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Transactional
    public List<Person> getAll() {
        return personRepository.findAll();
    }
    @Transactional
    public Person getPerson(Integer id) {
        return personRepository.findById(id).get();
    }
    @Transactional
    public List<Book> getPersonBooks(Integer id) {
        personRepository.findById(id).get().getBookList().size();
        return personRepository.findById(id).get().getBookList();
    }
    @Transactional
    public void createPerson(Person person) {
        personRepository.save(person);
    }
    @Transactional
    public void deletePerson(Integer id){personRepository.delete(personRepository.findById(id).get());}
    @Transactional
    public void updatePerson(Person person){
        personRepository.save(person);
    }
    @Transactional
    public Person findByFio(String fio) {
        return personRepository.findByFio(fio);
    }
}
