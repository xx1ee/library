package com.xx1ee.util;

import com.xx1ee.entity.Person;
import com.xx1ee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    @Autowired
    private PersonService personService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personService.findByFio(person.getFio()) != null) {
            errors.rejectValue("fio", "", "Данный человек уже зарегестрирован");
        }
    }
}
