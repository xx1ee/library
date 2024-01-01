package com.xx1ee.service;

import com.xx1ee.entity.Book;
import com.xx1ee.entity.Person;
import com.xx1ee.entity.PersonBooks;
import com.xx1ee.repos.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public List<PersonBooks> getPersonBooks(Integer id) {
        var list = personRepository.findById(id).get().getBookList();
        for (PersonBooks pb : list) {
            if ((int) pb.getDate_of_taking().until(LocalDate.now(), ChronoUnit.DAYS) > 10) {
                pb.setOverdue(true);
            }
        }
        return list;
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
