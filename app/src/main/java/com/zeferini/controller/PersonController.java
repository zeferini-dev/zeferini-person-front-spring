package com.zeferini.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zeferini.model.Person;
import com.zeferini.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public String index() {
        return "redirect:/persons";
    }

    @GetMapping("persons")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons/list";
    }

    @GetMapping("persons/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("isNew", true);
        return "persons/form";
    }

    @PostMapping("persons")
    public String createPerson(@ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            personService.createPerson(person);
            redirectAttributes.addFlashAttribute("success", true);
        } catch (Exception e) {
            log.error("Error creating person", e);
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/persons";
    }

    @GetMapping("persons/{id}/edit")
    public String editPerson(@PathVariable String id, Model model) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return "redirect:/persons?error=not-found";
        }
        model.addAttribute("person", person);
        model.addAttribute("isNew", false);
        return "persons/form";
    }

    @PostMapping("persons/{id}")
    public String updatePerson(@PathVariable String id, @ModelAttribute Person person, RedirectAttributes redirectAttributes) {
        try {
            personService.updatePerson(id, person);
            redirectAttributes.addFlashAttribute("success", true);
        } catch (Exception e) {
            log.error("Error updating person", e);
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/persons";
    }

    @PostMapping("persons/{id}/delete")
    public String deletePerson(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            personService.deletePerson(id);
            redirectAttributes.addFlashAttribute("success", true);
        } catch (Exception e) {
            log.error("Error deleting person", e);
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/persons";
    }
}
