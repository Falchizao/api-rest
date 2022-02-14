package com.example.posbanco.controller;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import com.example.posbanco.model.Person;
import com.example.posbanco.services.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * This method request the service the response of the persons in the database
     * @return
     */
    @GetMapping
    public List<Person> getPersons(){
        return personService.getPersons();
    }

    /**
     * This method request the service the response of the person solicited
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id){
        return personService.getPersonById(id);
    }

    /**
     * This method request the service to add a person to the database
     * @param person
     * @return
     */
    @PostMapping
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    /**
     * This method request to delete a person by its id
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
    }

    /**
     * This method request to the service to update the person by its id
     * @param person
     * @param id
     * @return person updated
     */
    @PutMapping("/{id}")
    public Person uptadePerson(@RequestBody Person person, @PathVariable Integer id){
        return personService.uptadePerson(person, id);
    }
    
}
