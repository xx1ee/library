package com.xx1ee.controller;

import com.xx1ee.model.Person;
import com.xx1ee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;
    @GetMapping("/people")
    public String getAllPerson(Model model) {
        model.addAttribute("all", personService.getAll());
        return "allPerson";
    }
    @GetMapping("/people/{id}")
    public String getPerson(@PathVariable("id") Integer id, Model model) {
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
    public String createPerson(@ModelAttribute("person") Person person) {
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
    public String updatePersonMethod(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);
        return "redirect:/people";
    }

}
