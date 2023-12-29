package com.xx1ee.controller;

import com.xx1ee.entity.Person;
import com.xx1ee.service.PersonService;
import com.xx1ee.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping("/people")
    public String getAllPerson(Model model) {
        model.addAttribute("all", personService.getAll());
        return "allPerson";
    }
    @GetMapping("/people/{id}")
    public String getPerson(@PathVariable("id") Integer id, Model model) {
        System.out.println(personService.getPersonBooks(id).size());
        model.addAttribute("person", personService.getPerson(id));
        model.addAttribute("personBooks", personService.getPersonBooks(id));
        return "findPersonById";
    }
    @GetMapping("/people/new")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "createPerson";
    }
    @PostMapping("/people/create")
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "createPerson";
        }
        personService.createPerson(person);
        return "redirect:/people";
    }
    @PostMapping("/people/delete/{id}")
    public String deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }
    @PostMapping("/people/update/{id}")
    public String updatePerson(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.getPerson(id));
        return "updatePerson";
    }
    @PostMapping("/people/updateMethod")
    public String updatePersonMethod(@ModelAttribute("person") @Valid Person person) {
        personService.updatePerson(person);
        return "redirect:/people";
    }

}
